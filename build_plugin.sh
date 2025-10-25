#!/usr/bin/env bash
PLUGIN_ROOT="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
MORPHEUS_ROOT=${PLUGIN_ROOT}/xnat_proxy_site
MORPHEUS_DIST=${MORPHEUS_ROOT}/dist
MORPHEUS_TARGET=${PLUGIN_ROOT}/src/main/resources/META-INF/resources/

echo " Morpheus UI plugin build: ${PLUGIN_ROOT}"

cd "${PLUGIN_ROOT}"
echo "Cleaning: ${MORPHEUS_TARGET}"
rm -rf "${MORPHEUS_TARGET}/morpheus"*
mkdir -p "${MORPHEUS_TARGET}"

cd "${MORPHEUS_ROOT}"
echo "Building MORPHEUS: "`pwd`
npm install
npm run build
if [ $? -ne 0 ]; then
	exit
fi
cd "${MORPHEUS_DIST}"
cp -rf * "${MORPHEUS_TARGET}"

cd "${PLUGIN_ROOT}"
echo "Building plugin: "`pwd`
./gradlew clean jar
if [ $? -eq 0 ]; then
	echo "Build complete"
fi
