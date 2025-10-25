
package com.radiologics.morpheus.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.radiologics.morpheus.config.ServiceConfig;
import com.radiologics.morpheus.exceptions.InvalidJsonException;
import com.radiologics.morpheus.exceptions.NotUniqueException;
import com.radiologics.morpheus.storage.entity.UserStorageEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceConfig.class })
@Transactional
public class TestUserStorageService {

	
    public ExpectedException thrown = ExpectedException.none();

	@Inject
	private UserStorageService userStorageService;

	@Before
	public void before() throws SQLException {
	}

	@Test
	public void createJsonOK() throws NotUniqueException, InvalidJsonException {
		String json = "{\"key1\":\"value1\", \"key2\":\"value\"}";
		String username = "admin";

		userStorageService.update(username, json);

		UserStorageEntity storage = userStorageService.findByUserName(username);
		assertEquals(username, storage.getUserId());

	}
	
	@Test (expected=InvalidJsonException.class)
	public void createJsonInvalidJsonException() throws NotUniqueException, InvalidJsonException {
		String json = "4g6lj46gl34j5g6l";
		String username = "admin";

		userStorageService.update(username, json);
		//test type
        thrown.expect(InvalidJsonException.class);


		//UserStorageEntity storage = userStorageService.findByUserName(username);
		//assertEquals(username, storage.getUserId());

	}
	@Test
	public void updateJsonOK() throws NotUniqueException, InvalidJsonException {
		String json1 = "{\"key1\":\"value1\", \"key2\":\"value\"}";
		String json2 = "{\"key1\":\"value1\", \"key2\":\"value2\"}";

		String json3 = "{\"key1\":\"value1\", \"key2\":\"value3\"}";

		String username = "admin";

		userStorageService.update(username, json1);
		UserStorageEntity storage1 = userStorageService.findByUserName(username);
		assertEquals(username, storage1.getUserId());
		assertEquals(json1, storage1.getJson());

		userStorageService.update(username, json2);
		UserStorageEntity storage2 = userStorageService.findByUserName(username);
		assertEquals(json2, storage2.getJson());

		userStorageService.update(username, json3);
		UserStorageEntity storage3 = userStorageService.findByUserName(username);
		assertEquals(json3, storage3.getJson());


	}
	
	@Test
	public void updateBigJson() throws NotUniqueException, InvalidJsonException {
	String testlength="{\"key1\":\"value1\", \"key2\":\"usk tyu cbj eir mwq nkl fzh azy ixf pnj lih ica qrh zvs rwg ipp stg szk ohd yqc kxn fuv low xcg rso owv gaw zbh utp jif gmt aog tym ybq pmf khr lyt dby wok bid usj tgf yns zro djn brn qsv qop rfd meu rpt kox top xtl cnv njm yxc kdw wug nao eow mqv fcv ezr nfr mvd oeo nlv hxm kjr spc dir cqh lqs ntf qdu det gau rfl fki lsl gyb vww dkh upz ypc qys qrk btr nel gld vmu fyh fwk klv zdk nvm lzo rdr qzy wdx ixs cus len ffy sdi pxz mqe ujl qih sqk ajs vtv wis plf dax kdo eeo mnn biw oug clt wqg kfe upf wpm vjh ndc wio kcp gka tzr hqf eih qsi qso iid tff oto xlm epe hrr inn pkp jnt pec avc cyr dwk wka dxp ztz mac izg iyp luc yxv ljj cil bhh bbk esj ihy siq ebw unz wjj kcg sdp lnn qqo inm coo jhx wzh lnu bwh ucb bfm uti kfb mmc lue qro tut cqs xyc kxk vpg vpi szb lzw xsn huw clr lys qsw lou zfl npn sot yha yqz xud fnf uxs rcm crv hhc txg gsf gkb hva svu fcn vna swi dys btc qep lti jme wue aoc uim pig njs axt ugb yda iuq qtp rro ewa xoq iap azj voa pxf onh bmi daq iph wan rdh dyn ulj fbx yuc qju ego gqa nai lxn xjj tbp lac zdi vkc ihd utl inl frc iwo hfr deu rrv qjj fjw kei ahz iiq jrb prm jrs the cqv kmw cll wls idw dkx ydb ysq jfk hts ttd lli sno var aex vss cph kei xok nwk xyh aqg qnx eac qxm exz zfh pzs axm ksc oha dkv qsu sxp wwq tho yit afi hem qvl ejd ios jht sit svg era bms gkj zpj xjt cch vrn idm qjy bgs fwb wmf pec uxa iel ifm riw hpk pvb ufg myw lfx lld whx ixe pzb vgh tze kgy pwj ooh bwc hbn wsc iep ssp xpw wfo gjb bkt bha rji efh lmg jzy edh ubs jld bbp dcq igv ycb ywj mne ylo vfc fyh drh isw aoc efg xoa fcg ywn ikq xpy vdy jse gbo qve xfh ler vbt pgd cos dzb wbq dgo vci cac lsc tvp qru gmj fpd pav ekk oti lre rpg yxd gfe xpi nzm hpt ogh zhc hxe nvn evw kod jdi mii hry jiw jsm gbu zgr vzi eal jjc bmi xbe iom jlz gxg jam sdt eep wxn weq zaj tmk ydp nuj ipc pzj whd jtr esg atx qui knp pyb mvf ugd tss gzm rno lfi gyq yrr byc bmb wmp ywo cht nwq bsl fco mpg qgq fbb yeq qik xto oqf bsr qtl lmd xgf ajv ict mxu qgz mmk bpo qqy ezs sda keh phk pau wwk ekf nxy mde liq gab epv uql ktn yni hxk aan gcl bfc aim mbr xmg zfg qcg zqh xhu zct zyp rem hwc esf bbe mam amq cmj txf wcs pqe dqk ebf qdk kyl ftx aey tas dgm nct hvz qdg gij kvn bqk cdz cyx nex tzb iiq ihr cel kir nit eqm vgf kxz cvt gvg jmw rkj ozz iij sjl tcf vav rgh xqm eof xzd jky zgg bbs mbz ofu qgp ddq isf fon mea pcd tor onm vyz bms lxo bor jsw tpu jvx hqd sdw qxn wfp ryt glr kam uqs ywv efn wty ecf mmy hdv gbx ufq eut hgw rxu uxh trt isb dqy ldg val vov xuk gld acj vbv wjf ngv pdp bch iav lkj oll vuk ysz zpk dhq ulx qwm imd szd wci skc bvv gtp ncq nat ihj ait mqm ppd tdp nko vaa gwj jtm egl kpi yky hjr cda rho tdo eiy nzj ysy boh lzi eez cgr lfk qyo yow ise jnq rcz sgw png cot fll iqt iec utr qhy zmo eta mxy tio dri uqi qpn qzx gga uyz nnj czs fso zat jii bis mbw cdu iaq yec jdf vjb zeg xpx akj rph fft nny qik uvb dci zaf gzk ogo ndz yxv gdc jqv gcn kbw hij lmb dih bqy rle ytz cyd kuy zpm sfs lhc rle iwf djs igu gpt fjv adv cej cod sde izh uhy dyb sci jjt rkx qye rmd kwg naq uid qsw lum xqf ntj osr lxe adm kyc riv ect gjd ibe sgx jrf wma pxv yos dag sbw dws qsx uws tzl oux roa pde tpl fqx kpt gem tsh zqv dof bvi kjr ymf zoz txb ulo fun lsk uup tmd gjl txs whj wkz lad xqq iiy hmg bve rnc yob fde ejd nwv twn zzx eps tch cmq krt wii aok tmc uex dib jyr izu gkh tjy saq zej hrg uxv zrb jft aka eaa uqb wts ncx txn efe lyf sqa kmq row ayh jax pzp onx vkp xkh ebr bxz mth sdd wty zdl pev hwh sza enm qhr kvr uhi zpf kdm had yfn wao eal zfu cpm jvj vbj bzf gpu flq osf clw ksa lmj vzj ysd tlt ifk osi zgk ktt hya emg msj zuz kba aes btr dej lny xam glg mby vej qys ayp myw xac zim dnz uqd ubb jyc tga fgw ohq xqb wem vec vuf gba enh xds ebn dnv cma vtr qmf boq ttt lub jxw hmw jdk dll grl gbj kdq kfm zbe elm tka uzp sjb zyc wiq xat psg jvk wes ldd ehs wlh rfm kxz qaf brw jvm zyn lgz lra jfl wvq rtm jsn cfh fcr adg opt ftz soh hwu ogg dem kmt aje sxs bzm dxf fpq jvc amx vxy nrd ubo dju faj uli aqc mpj ldu lff ysb gbq fjm sje rch kxe tyu rta rtt xoo hbg gsc pda ato vgh bxc kku dmr kcp bnh ois btx uec xht xil nuj bom tuy phg lzj aic kiz fty inf vrb rvb wck gkx iqf afp svy cou txf ugr zbt yut azq kof vtv new slo kky fkt dad wqk hpl qpj ecz zyz ifo tzs fsa krw sjh yjo ugu fxk scl fzu zpu lom oxo jyt zny juv rib qot pai nfk bcd ggt hph zog ibz rcm spf cxg iam bsp jkz vxk ldo gsx yhq lxg pac sno eax gos tmq vxl dts quw caw cuj xny mip brj rkw fxx nwg opp pik uiy ocq ayj ayp pwq chq wza jqy yjf kep izm vnr pcu zns itl wpm pbi kbj dzm xxw iml zpl ocf icy ydn ijd rcx yks yvm smr klw yjk wcn xzm htp bgj hjn zfq exx gng kkw stf jqo vaw nwn suk vsr rpl ncv ira lfd tyb kji ctx dlr gyf pny jqn mup tgx ted hfx wsl ywp fdk zwz ich wcv llw agu qll yke vbd abd mbx loy fjp npl ppj zkq jwg kdc dtn wpl czw pfs unv thb rdr zhp hsq urt zpt jys emn ohe lsl rlh whm elo but acq ekz osn tuo qgp won bnj cdu pbj kub rto hwt nhl rkx fix fsb xzj ejz clz uhq vvp yns fok wvb stl dma xge gee qhq low qfr req lnb epp ncb qqp zdy hvo fim dmj vnz uyt cqj vtb igm goh zwl blg yav xiz cnx vzh fxb mai pci zow emr phg khy kxy klp kxw uwx hbu bmr she cul him ehz adc bti jjk ydg rbw uqn szn oxr dow tef cog rnn mgv pil eyi kqf dxm unn rph lha gcc oot iec nmf gbq vuf vum hqj eqt emf ige lmt nom whe gwd tqw afu kzn nxf qkw pih jyv fsm feo ugr mvp cen nys vvv ljf zvg nmv uqu eyi pli veo yus pth mot akk pue dff eqy asl bxl ssp wnw oev ous fdd wpo vyu elv yqq wii rox ejy exs kqv ruw tje znx bas jvh xku fqg lbz oak rji ebl dln ayz xip bcq hed zvk jwl osv wrb dqr wog kly zkz uoz xhv dxi ssu unr ipe swx mzz prb djc juz rli fhi obl yqn qci clh iza wpo khw jov nyk oyh lii sla eks pwq vqo hcb ymu ioc udd iwo npt dih yli lrj xbk ayc zfg cgx ulw vph wae nes srb eys wui xrk dqa gpz gya fsy oiv wbu tqf gbw qyn sce qzs sko toh nwy lfh kwy icc ffd vdf mhl tow ltn dtl xpi kwd jvl ahi hoy nkg vfz gch tss ocn knv srb bzv zsl uzc emt qkb yeq uqf cbt sef txr qmi mkm slk cbp tom jtm nne pfv igy hhf slw xxx idv xkl tkl gte cma nyu ntz nox dye fdn kti urb cge icy ukm ega eod xwz yfn ger kyr tdo mqo qzj jjn iha cjp ftl hll xti wyq ljq uxk cqj xvd tzd fpj hbv qzi kbi yjv pcp mon olv mno lmn azu nbs buo ttp mwg mya qfn mrv obh irg vdq ayw ctp opt hfb naj vhk ctl lkk jen ysm yqk ael ijt frt voj iet gis ftm gzr sxe yxd cmc mya wfp dgd rtn koy kil gea ius vnp cvl wek uwk iai lto mhe bhl fmm axc wau yru bef ubb scf byk pgt lsp cph iyk fog exw gmk gix qso kyd zer bav qaf vks twd ach ywy vxx kdz ass via uxt ojj dwc qct twe lxa xun pug hqd dqt biq iao vwg gvu snr ppp hkj wtq xzw rjt slv zhw jph fyd ncj ajd djr tcn kfz jcg ich dql eaw oeu ale iht lov qhi cfz gez njc vht ozo cwh wcw fyw ynj lvu cvf dvs ner rxc diy phs ewh ecn cct gxv xgg lhf cvs vfl dde nqv sjd ztn cet ysl xez kis rxr pqr log wae ydw xur lpu jmz drj bvm iqd are gfq amn yja tbc pas ses hay etn ser yxw azj bay wys gsn dzq vlj zzq uxn nlg zxp xbu cwa drx pln zhd tid pur hxz pta nlm qhi tkc wwf ygt ffv nzn lat zuu pgv mwv uon vol pnu bjw ltl csd fqd xld eqy xew vja bsm fop njt jgo aml vdp htx jko pkk jyp jsx kvb nxr bng xmc fxd zka ybh ura lbg mrg juf mgt usp koo nyr ckt lsw eun dnw dli usk bbv osj ifk sbq cbn ngp pdh mqx can ccv ryb ivv gpj axe ach ire fdp doj tvw jkj mcb lye njf xmy tlq bhj tdy jnj dea qyr rdw fgk apa htf qcc mcg phy ozt uwu lim tag emc kir sts zio orl fph nbx ohn fbu gxj ntu ogv cep bwu zhd qxf hlu nxx pix itv szw nni kgr qvt twd cci tzl mqy ndp lms ilc yht pgf pli sdx ago czd mjd zwa lbs gbg ibe fqj zdk aba ony eof tdm tpm jbd ifx ncb mma nyj fdt prw fmd bwl nfc syl nmy yur qvj yaa ssu dnd ybt peo duv tuq lkc yri tbt gzz rkg djr wrn oen slb pui vtg mhb qrg vpr fud ukh leu jlg xjj jqs wqg unn rhz xgi xvs tab lmg cck mqn gxc dgk xcg buj dpu vhz afh phk iok tbz igs igu jmv xjn ydu qyo cge cxg azv vrf gpe bsz vfn unf mrx xrc gup faf gfd wvd hzx xxh iej zky lru quk xya yyi pey nzo hgg jql ohn eqn rre dxo raa xvz vgf gwa vms rya bjf qnk ugq nsh bzt rtn rof zxo okm yal lzr jni vyy kub hva pbf ijp klr ayt pyt klh vou xwm lzo wzf emy miq eyo ohh ukn cjn wmt umm gbj atg lyq xgj imu fjd vym jxd mqh xvm pzu chl hur qot efi eqt wyb pxp men hpy pur gwc pom lsk yhf mvu dau wqs clb ejm zex pcz edo mxz cne zje sgu ieh wlj hbi laa xgk jnz cbf sfe agh vkj dqk vfh uyl bju egg fpw map utm sng cqw xgy aet ioq tyb ucl eos cvi wge bgy rld xhs zkv umm rwy zqu qrs gvb aly kij xkv pbm uqe nrf ycr ypv zva zfb kdk lsn wvw quk fgm ueg vii ltj hqv xbn hkz vct ece bmd sea fel byl fuz fub jpl avd zrh rbt tzg igj tto sma ycu qfq yjx ljy xkp qwu oyi zsm rhy rmb apw fcl pht edz zio crk mnf src rby blw vqy atk mxf dts xca cgx zwc vyj vuh fvo jwr mmw yid xsq brk azp yjx vtg cki neu hzt fks pmj mae jsi sba brn hsh gqh hna weu fia dge tls bhf enh fci azv jwm xdj vnn ewk eic yjl btx usk xub cji wku vej dmy cfd loj yuy nut moa guu kiu vdn aqi smz gpr fmz sue xil ijg hfl ezc vzz kzh mxu tkt dfr dca uzx afv pdc xao goe ebm epn mjz ukk wjf smg iom xpw gpk ciz dng eux rgq qlv lhi amd jgo vjh cib xid fil uwf jcx zqe wed epn bfe yso tme ilw bhy wgc rnf cpb twh wpe rvk kbu vsr cde tzu zjc qgl mvx ufm jjh hps whh blh irg zfu pvb hse mrl yzi lwo lir dyv bwb vfo mfi kqc wlw dvc iet fir wuu gxb zym jze wlz huo jeh ksp gao ylp ovo nky odd qcx ihk zzr qnw zvl haq lnp qhv hzt kln wku wnd pyo rrq nxd kcv qel ifj ser qom vuq stp lwm fml mni uqs fmu bmw lbj wlg nfz zrd ueh usp wps zkj wmj irt zed hmm hcc tow edp des ksv pgl bek doj uxt oxm pbu rkw wpf smn yzx gxx rsw bkx tgb edk ocm bbj skr inp rxa fqr nln ili vok kro lxb axe vom dto sih gam uzn yak zbs qtu gdf mvp lfq sfb zgi stb mtl nll veh mgg aos hhe bzm bex krt net cdi tsp iyp cki zzd pup erf sfk vbz nkm kdm qkl vuy tad eyj dpx naj cdi xzy ckj aug zdo tvt llf aha jnl izr zaf lwp akl mqn cbn ckk adb zvw zrw gni uwp xga grp bna ahw yzn gdn wiy aih hfi dmo nzw exj jkr hww ner usv fqd qyy bqg bxh wda puw fvs mgp niq idz fuq ngo pbd rzs fvh ukt phy dzy dyp gqw axs lka yqg fww pga puf kjk edv snn mlu drx mjl vlv gtj jtu rbo bus opw yoc jae jal jmi kxo cyx mrb zrg ucg uwz fae wuc kvw pkd tar ceb xnm qip jwd ubr uuj iqv loh kxq nse wct ods gim vkw uaw nfq qsh elv gml qoj qja lxm bus ify mqe rku btu ogy ghy pvg ehy kzn xol rcn skv mas nxu ska wda sno lia upl mtr ofm vgk tdu raa kpa jbc wbr swg fhy ojp jrz brl sjp csa obf ozc kzo dmt bqh cuo nwo spt ial avd mcp ziq eru yfb das opy uuz qsq wtz twg dxr pxk gbc jzf frp yjn axp gyv hvv cgz prs nmj zjo zys kpt huu chl urd pdj czb wci wvt poq whp uwt moa cna ylr ecn rdn zvs xux eoa wyg mue pkq rfm spy bxz dzr xgu njz fyk hiv qcf dei khm ngi bka lny btt ehf icm xky twr qih lbk ojx vud lxg csi mjq quv ejp cfs jke dce srd dyv kfe kvq ebu ckd see nnq xdk jlj vdk yna agd xkk fil cod vrf xud fea orp sqe kbu kze euf tng mqt aqz msw rey wdt vnx hxa cie ouv jbb zfm wgy wsr ill cvy xlm muy edz apk tdh iqf grt rqq ksm qzt zkk rgi cnk ejl aqn mmt lxb gtr pll iug vod cwu sir bjt syx jxt djz ily jzk ysg bav rfp ifk gxl hbw hrs uwk xkf jhn utw zof avv lss mqf gmi xuv uch csk czn pto zyw ftr wvq wxs svk oob svh zji dlf phy lwb ody jbk aay wwj fet faj cwd yno hir qbu ovk kak jio ked bbi ubm kvx zru yac tkp ofc ewb ili kzm sld rdv dmd coz cde szy xca jid mbt wob qvb kxf uey ydu ceg dlt drr qfh yzr csj hor vgc mag bxb vgu myz djj ahg gzr dbs cqi jxj stc nss bka mah nxp oey yzs ada uvn sdg deb pgj azf bcp rye rqg emc gli fij gcw kjn ugw cha ubt cdl xzn hha wts edq hms jyo mzy dia lig gru krg dui nhh glz nrv khz pzj bea yut giz wlo cqt ueb oqj cwr qpu vus fwi fqt hfl mcw cdf vtx dwf pus aqy zgx dta jdz cno jkv oiw kpv gvk kec vxp swz zjo exm sky kkk paz wir uzm hjm axg xkx xbx cyo yuf dfn ygz xqu uih nvy xgl ltv ekk lbr pvx yur adk fcx vmi zec enn olw qts czq gwd vox fon ajv dyg mjv mqv lwm gsu ntj mlh yfg unx vdc zkx vdh gmw sdk ooj bja idx fts ils ing uln jka cxy fel iol kih vfv cde lnv gxe umn twx til isr oyh kzj coe awr ysk srk wia tzl ehb jlk qvf jxy vrx ygo hxr jzg dln zse mzx ong tcx mrw ftd bkj uzp utl bwv gwd nkg jak ffu ldm vse wgf pdi aqt wwd wyz jnl fwa uao ysu gcl tpr fmk sbr xmv qbq qye ksg xzl cxy nvb ebl ros wtb oas day pay jfu fgx lsx tkw qor nun rzl hxu lhu jjy wvg tti bdv hhj cpx xcl nxl iwg zri slg tyv gmr vul wkk sig ndn xji twx ykg ssm nux ohy jeo xpp row ceb dhg ito kmy yiw dun apy laz fpx bwv uyp rsy aba lub zwt umq kbk zbk qxj nfx ggy wvv jwr beu ehy aif ggd gpx xhp fwm moq mln lke kya hop jqu bmy ymy pik wsq zgk fon wup vla usr day lgj tzi cxe fjy hrv rbf zkr ojh hgg vxl qru fah ipb nle ptl cvf noe tqq zjv ids plc hhn mbh rdk yma rwe fum gxb nxd tce wiu acr emn wtc xwi niw zsb gro fnu guc reh wyc fut hnn gjz znc cvo ubi uyd dgv fex dfp wak gaw pzd yyv bws nwx rdz vtr ghf odx kgw uic iqc lzp upa vrc qns zhe wam ice vod utx fww trh gzv gxj wje bkc jaa dxl vws mop nfj zpi pmk tfz upp say isb nvu ann wlr luy out kcq ibn ygm tnl tvw voc glb rzv yyp tek xtp mol hys tev yka kfx syq pxx tcr lfd qnb vhj obi fjw eup kfc nhs hpw fgr zdq mye dbj azl eny exp rsq uqg biy ool srt ctf azc aqo pxi oxq puv noe lzw hvq rsa stq ibp sae cgh dsj ram smz rsm vwy ylg sdg kmq tex bcs zwz kvv eqf zym vuo mna hfe djq tbs xbi zbc avc ect kcg tlj raj gvd bkf vce pmz sgc roh fmr ucq ilj bvh bgq sjs bkr daq oid wpx rqx jfo prv rfw oap xht pmp zsw uwv vek euu wuy nab fgc avb kpr ena hwr hun gxa jzl jxl ups rct zty eyp rge hfs ath znj cdb prr gzx std mhs fyl ukm evd rlw ybf zgr yqu gcj ckh lye cvt kqs pyr pps hfy vgq jyy zwz vwz ujl bcx nvn zia cgl spi zkd jaq gpf bhs gnu min kiz elb ykm qrb ixt dqe sbd pmp fvp pfu fgu wua lql qlr cab zko lsp zxo zos yqp vwc mod jfa aip aml wjv bcx noa tef ywo anu lpp cpm xqf sza qfj eoo okw lqz rnw shf hhh bef usl vce jjv azj cxm tgf xtx wlq vju exf fqd iti rlm wli wae gzi mwk cgm ucn ism olq pgq mhw rog tip gfb twb dll zpa wqg owv wwa ocu uef vij oow gce fxu pmv ucf zkf qbm kyl ffh lkn dkm zlj pth bnf dyg dlr nzl baz aot wss jrv wxj xzo omg gvo bbo gqr oox muv inl miq vvg inh xjq bfc qlh lqu owl bkk bja xbq cbr mch neh ezk xdu gvr bvh baz sed dxf nsx pvq wsw wgs rtu pll lrk pbe qem jqi ocd gss bql azr lay bjt udm vgw deq ffm mkw byu xkj lzj ewe fwl ots vif ezs zbd uws xus nav equ dgx wpn scv bbs oxz ofx ptz ocj fwb hvy xnt jqf hav ncf abp kzg huv yuf gez cfx iuf ndd byr enm ulm dae aud kmv sek iah qxa nvt yqt dha xcq ogg pte llx zkv hvf vvx wav voz bsi trb ili tfx pod cop dzq jhk vet whn kyc hof dhp mzt fjn kla eoa nkv nvo dpn dyu fap lst tpb pcn bny cfi kvk zdm xow lvt zpq gyw ugu szl nzx wks onl wur pxv mgc xtz gks sqr utt vws zyn egh rdh kfb igq iwo nom oiv vfo oek tvl nic shn evm uww gwn vav npw bkx ilg avz dlu nlr ijg gkc qrs pgk mfs zlq woy sgy wuy oao sep tdh wfd bnt ddx mpu pbq veq chg dkt kzx tws tyv vbq jqy gtf fvq ftg nca udv knh nsp ydc qvb ziv cxb tdp xcv lbo qtu rrf gni jix ued cet ttu qkv alh ghc rki vay nnp aef zcs hfj ftr jaf trs bap aca vfw tgn ret oei fag aes rho qxw gcf vhg sry etz yqh oxr pzl bme elr qjm kog ppf wnx ffw mwd plm ecs dyh uni ujo qdh yif imu erb pad ozv zzm zkj ecu mna mjf fim jxa oma ffw fgm mdr rod czl gdo myq fcw snh oun jxn ntn mfw mkb bya oeo eox swz wkz iqv fio kmy zvs clh lty vck ymb wku vsh lls yts lvz esc drd hsw wbt mmv etc bkz wuh mfd crv eru jxd yfq ayo xti hxp mdk mce vly del llw pum ejz rog jfn wul bly qvh joj xtc oio dma gtf ryk zxz sla ubh paf bsf zhj tkl ojc oyy kbv mlj suf reb fwm vjz urd qmy pep kbt afg uhp xyb jcj uly oxq ezq biz hby zdf mpm zgv ohm wtf xmt vad xpj qqo qnc lys bsd pls pum slp ybv suf fwb izc evc aof hdl ozq vbh osh kul krv qym pec qgc bab cch tiz vgw hdt qjc doo bup hzt okb eok pyc jkr dkg hoi rgw hqi bax qrd wat ben ddd zku urc yhc gfm gxy jne adz npy jcb ojl bax ooo xaa rty rhi tpp ipt juy xjt ono vkc htj ifg gyy tsu wbu exn hrg udg pho qbx ght eia oxa cpg jla ppc bnp xpa iwa htf flp uac kiy gaz iiq pic wuk fha vph brb cyw yjx quk xoj ixk bon gmv gbq eeb bdd oxp kft nnh gce wdl fax xxy vwf wjq gns hen lbh fmz eiw mnx aff aro tie gfs ity pyv jsb jdg qyj jqz kcd ztu jlw fmf xkf vis fus dex emb sqn bbi ige tcp gzu uvs ihd dqn yeu gkn xhw bws fve xyk cnw jfr uec naw qrk onb hvt zsy zsq qjy lni vpg zyj gqw mrf zvh xtr cxb yyt tca szv lnp iqn chg msi ffz khh sfo rcr nje zze tsr uzx iej xba tmr ode okf fyn iyc sgn twu sak epn qzi sgy hay jln bpu glj\"} ";
	
	String username = "admin";

	userStorageService.update(username, testlength);
	UserStorageEntity storage1 = userStorageService.findByUserName(username);
	assertEquals(username, storage1.getUserId());
	assertEquals(testlength, storage1.getJson());
	
	}
	
	@Test
	public void findByUserNameFailedAndReturnsNull() throws NotUniqueException {
		String username ="admin2";
		
		UserStorageEntity storage1 = userStorageService.findByUserName(username);
		assertNull(storage1);

	}
}
