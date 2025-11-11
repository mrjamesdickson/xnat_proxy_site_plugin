#!/usr/bin/env python3
"""
Analyze XNAT download process using Selenium
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.common.exceptions import TimeoutException
import time
import json

def setup_driver():
    """Setup Chrome driver with network logging"""
    chrome_options = Options()
    # chrome_options.add_argument('--headless')  # Run in background
    chrome_options.add_argument('--no-sandbox')
    chrome_options.add_argument('--disable-dev-shm-usage')

    # Enable logging
    chrome_options.set_capability('goog:loggingPrefs', {'performance': 'ALL'})

    driver = webdriver.Chrome(options=chrome_options)
    return driver

def login(driver, url, username='admin', password='admin'):
    """Login to XNAT"""
    print(f"Navigating to {url}")
    driver.get(url)

    try:
        # Wait for login form
        wait = WebDriverWait(driver, 10)
        username_field = wait.until(EC.presence_of_element_located((By.NAME, "username")))
        password_field = driver.find_element(By.NAME, "password")

        print(f"Logging in as {username}...")
        username_field.send_keys(username)
        password_field.send_keys(password)

        # Find and click login button
        login_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit'], input[type='submit']")
        login_button.click()

        # Wait for page to load after login
        time.sleep(3)
        print("Login successful")

    except TimeoutException:
        print("Already logged in or no login required")

def analyze_download_flow(driver):
    """Analyze the download flow"""
    print("\n=== ANALYZING DOWNLOAD FLOW ===\n")

    # Look for download links/buttons
    print("Looking for download elements...")

    try:
        # Common download link patterns
        download_elements = []

        # Try to find "Download" text
        elements = driver.find_elements(By.XPATH, "//*[contains(text(), 'Download') or contains(text(), 'download')]")
        download_elements.extend(elements)

        # Try to find download images/icons
        images = driver.find_elements(By.XPATH, "//img[contains(@src, 'download') or contains(@alt, 'download')]")
        download_elements.extend(images)

        # Try to find actions menu
        actions = driver.find_elements(By.XPATH, "//*[contains(@class, 'action') or contains(@id, 'action')]")
        download_elements.extend(actions)

        print(f"\nFound {len(download_elements)} potential download elements:")
        for i, elem in enumerate(download_elements[:10]):  # Show first 10
            try:
                text = elem.text or elem.get_attribute('alt') or elem.get_attribute('title')
                tag = elem.tag_name
                classes = elem.get_attribute('class')
                print(f"  {i+1}. <{tag}> text='{text}' class='{classes}'")
            except:
                pass

        # Look for "Actions" or "Download" in top menu
        print("\n\nLooking for Actions menu...")
        try:
            actions_menu = driver.find_element(By.XPATH, "//a[contains(text(), 'Actions')]")
            print(f"Found Actions menu: {actions_menu.text}")
            actions_menu.click()
            time.sleep(1)

            # Look for download option in dropdown
            download_option = driver.find_element(By.XPATH, "//a[contains(text(), 'Download')]")
            print(f"Found Download option: {download_option.text}")

            # Get the href
            href = download_option.get_attribute('href')
            print(f"Download URL: {href}")

        except Exception as e:
            print(f"Could not find Actions menu: {e}")

        # Look for direct download links in page
        print("\n\nLooking for direct download links...")
        links = driver.find_elements(By.TAG_NAME, "a")
        for link in links:
            href = link.get_attribute('href') or ''
            text = link.text
            if 'download' in href.lower() or 'download' in text.lower():
                print(f"  - {text}: {href}")

    except Exception as e:
        print(f"Error analyzing: {e}")

def capture_network_logs(driver):
    """Capture and analyze network logs"""
    print("\n=== NETWORK LOGS ===\n")

    logs = driver.get_log('performance')

    for entry in logs:
        log = json.loads(entry['message'])['message']

        # Filter for Network events
        if log['method'] in ['Network.requestWillBeSent', 'Network.responseReceived']:
            try:
                if 'request' in log['params']:
                    url = log['params']['request'].get('url', '')
                    method = log['params']['request'].get('method', '')

                    # Filter for download-related requests
                    if 'download' in url.lower() or 'archive' in url.lower():
                        print(f"\n{method} {url}")

                        if 'postData' in log['params']['request']:
                            print(f"  POST Data: {log['params']['request']['postData']}")

                        if 'headers' in log['params']['request']:
                            headers = log['params']['request']['headers']
                            print(f"  Headers: {json.dumps(headers, indent=2)}")

                if 'response' in log['params']:
                    url = log['params']['response'].get('url', '')
                    status = log['params']['response'].get('status', '')

                    if 'download' in url.lower() or 'archive' in url.lower():
                        print(f"\nRESPONSE {status} {url}")
                        headers = log['params']['response'].get('headers', {})
                        print(f"  Response Headers: {json.dumps(headers, indent=2)}")

            except Exception as e:
                pass

def main():
    driver = None
    try:
        driver = setup_driver()

        # Navigate and login
        url = "http://demo02.xnatworks.io/app/template/XDATScreen_report_xnat_projectData.vm/search_element/xnat:projectData/search_field/xnat:projectData.ID/search_value/UPENN-GBM"
        login(driver, url, 'admin', 'admin')

        # Give page time to fully load
        time.sleep(3)

        # Print current URL and page title
        print(f"\nCurrent URL: {driver.current_url}")
        print(f"Page title: {driver.title}")

        # Analyze download flow
        analyze_download_flow(driver)

        # Capture network logs
        capture_network_logs(driver)

        # Try to click the Download Images link
        print("\n\n=== Attempting to click Download Images link ===")
        try:
            # First click Actions menu if not already open
            try:
                actions_menu = driver.find_element(By.XPATH, "//a[contains(text(), 'Actions')]")
                actions_menu.click()
                time.sleep(1)
                print("Opened Actions menu")
            except:
                print("Actions menu already open or not needed")

            # Click Download Images
            download_link = driver.find_element(By.XPATH, "//a[contains(text(), 'Download Images')]")
            print(f"Found Download Images link: {download_link.get_attribute('href')}")
            download_link.click()
            print("Clicked Download Images link!")

            # Wait for download dialog/page to load
            time.sleep(3)

            # Print new URL and page title
            print(f"\nNew URL after click: {driver.current_url}")
            print(f"New page title: {driver.title}")

            # Look for download form elements
            print("\n=== Looking for download form ===")
            try:
                # Look for form inputs
                inputs = driver.find_elements(By.TAG_NAME, "input")
                print(f"Found {len(inputs)} input elements")
                for inp in inputs[:10]:
                    name = inp.get_attribute('name')
                    value = inp.get_attribute('value')
                    input_type = inp.get_attribute('type')
                    if name:
                        print(f"  Input: name='{name}' type='{input_type}' value='{value}'")

                # Look for form
                forms = driver.find_elements(By.TAG_NAME, "form")
                print(f"\nFound {len(forms)} form elements")
                for form in forms:
                    action = form.get_attribute('action')
                    method = form.get_attribute('method')
                    print(f"  Form: action='{action}' method='{method}'")

            except Exception as e:
                print(f"Error analyzing form: {e}")

            # Capture network logs after click
            print("\n=== Network logs after clicking Download Images ===")
            capture_network_logs(driver)

            # Now select ZIP download type and submit
            print("\n\n=== Selecting ZIP download and submitting form ===")
            try:
                # Select all sessions checkbox
                try:
                    select_all = driver.find_element(By.CSS_SELECTOR, "input.select-all.select-sessions")
                    if not select_all.is_selected():
                        select_all.click()
                        print("Selected all sessions")
                    else:
                        print("All sessions already selected")
                except Exception as e:
                    print(f"Could not select all sessions: {e}")

                # Select ZIP radio button
                zip_radio = driver.find_element(By.CSS_SELECTOR, "input[type='radio'][value='zip']")
                zip_radio.click()
                print("Selected ZIP download type")
                time.sleep(1)

                # Submit the form
                submit_button = driver.find_element(By.CSS_SELECTOR, "button[type='submit'], input[type='submit']")
                print(f"Found submit button: {submit_button.text}")
                submit_button.click()
                print("Clicked submit button!")

                # Wait for response
                time.sleep(5)

                # Capture network logs after submit
                print("\n=== Network logs after form submission ===")
                capture_network_logs(driver)

                # Look for dialog
                print("\n=== Looking for download dialog ===")
                try:
                    dialog = driver.find_element(By.CSS_SELECTOR, ".xmodal-dialog, .ui-dialog, .yui-panel")
                    print(f"Found dialog: {dialog.text[:200]}")
                except Exception as e:
                    print(f"No dialog found: {e}")

            except Exception as e:
                print(f"Error during form submission: {e}")
                import traceback
                traceback.print_exc()

            # Keep browser open for observation
            print("\n\n=== Browser will stay open for 30 seconds for observation ===")
            time.sleep(30)
        except Exception as e:
            print(f"Could not complete download analysis: {e}")
            import traceback
            traceback.print_exc()
            print("\nKeeping browser open for manual inspection...")
            time.sleep(30)

    except Exception as e:
        print(f"Error: {e}")
        import traceback
        traceback.print_exc()

    finally:
        if driver:
            print("\nClosing browser...")
            driver.quit()

if __name__ == "__main__":
    main()
