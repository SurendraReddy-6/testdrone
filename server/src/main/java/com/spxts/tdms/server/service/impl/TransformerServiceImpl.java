package com.spxts.tdms.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.spxts.tdms.dobleUtils.AdminDataType;
import com.spxts.tdms.dobleUtils.DataModelR2;
import com.spxts.tdms.dobleUtils.DataModelR2.DtaSessions.DtaSession;
import com.spxts.tdms.dobleUtils.TransformerTemplateType;
import com.spxts.tdms.dobleUtils.TransformerTemplateType.ArresterNameplates.ArresterNameplate;
import com.spxts.tdms.dobleUtils.TransformerTemplateType.BushingNameplates.BushingNameplate;
import com.spxts.tdms.dobleUtils.TransformerTemplateType.LeakageReactanceNameplates;
import com.spxts.tdms.dobleUtils.TransformerTemplateType.LeakageReactanceNameplates.LeakageReactanceNameplate.LrFields;
import com.spxts.tdms.dobleUtils.TransformerTemplateType.LeakageReactanceNameplates.LeakageReactanceNameplate.TcReferences;
import com.spxts.tdms.dobleUtils.TransformerType;
//import com.spxts.tdms.dobleUtils.TransformerType.AdminData;
import com.spxts.tdms.dobleUtils.TransformerType.Arresters.Arrester;
import com.spxts.tdms.dobleUtils.TransformerType.BushingDesignations.Bushing;

import com.spxts.tdms.dobleUtils.TransformerType.ExcitingCurrentTestSet.ExcitingCurrentTest;
import com.spxts.tdms.dobleUtils.TransformerType.ExcitingCurrentTestSet.ExcitingCurrentTest.ExcitingCurrentFields;
import com.spxts.tdms.dobleUtils.TransformerType.ExcitingCurrentTestSet.ExcitingCurrentTest.IwcData;
import com.spxts.tdms.dobleUtils.TransformerType.ExcitingCurrentTestSet.ExcitingCurrentTest.LinefreqTestVoltages;
import com.spxts.tdms.dobleUtils.TransformerType.ExcitingCurrentTestSet.ExcitingCurrentTest.TcReferences.TcReferenceSet.TcReference;

import com.spxts.tdms.dobleUtils.TransformerType.LvttratioTestSet.LvttratioTest;
import com.spxts.tdms.dobleUtils.TransformerType.M7BushingTestSet.M7BushingTest;
import com.spxts.tdms.dobleUtils.TransformerType.M7BushingTestSet.M7BushingTest.BushingTestResultsSet.BushingTestResults;
import com.spxts.tdms.dobleUtils.TransformerType.OverallTestSet.OverallTest;
import com.spxts.tdms.dobleUtils.TransformerType.SurgeArresterTestSet.SurgeArresterTest;
import com.spxts.tdms.dobleUtils.TransformerType.TestConditions;
import com.spxts.tdms.model.ArresterIwcData;
import com.spxts.tdms.model.BushingDesignationDetails;
import com.spxts.tdms.model.ExcitationExcitingCurrentFields;
import com.spxts.tdms.model.ExcitationIwcData;
import com.spxts.tdms.model.ExcitationLinefreqVoltages;
import com.spxts.tdms.model.ExcitationTcReferences;
import com.spxts.tdms.model.ExcitingTestResults;
import com.spxts.tdms.model.ExitationTcReference;

import com.spxts.tdms.model.ExitationTcReferenceSet;
import com.spxts.tdms.model.LeakageTcReference;
import com.spxts.tdms.model.LeakageTcReferenceSet;
import com.spxts.tdms.model.LeakageTcReferences;
import com.spxts.tdms.model.M7IwcData;
import com.spxts.tdms.model.OverallIwcData;
import com.spxts.tdms.model.OverallTestResults;
import com.spxts.tdms.model.SessionAdminDataDetails;
import com.spxts.tdms.model.SessionBushingTestResult;
import com.spxts.tdms.model.SessionBushingTestResultSet;
import com.spxts.tdms.model.SessionCablestates;
import com.spxts.tdms.model.SessionLvttRatioTest;
import com.spxts.tdms.model.SessionLvttRatioTestResults;

import com.spxts.tdms.model.SessionM7BushingDetails;
import com.spxts.tdms.model.SessionM7BushingTest;
import com.spxts.tdms.model.SessionRatioTestFields;
import com.spxts.tdms.model.SessionTestConditions;
import com.spxts.tdms.model.SessionsBushing;
import com.spxts.tdms.model.TransformerArresterNamePlates;
import com.spxts.tdms.model.TransformerArresterNameplate;
import com.spxts.tdms.model.TransformerConstants;
import com.spxts.tdms.model.TransformerDtasessionBasicDetails;
import com.spxts.tdms.model.TransformerLeakageReactanceNameplate;
import com.spxts.tdms.model.TransformerLeakageReactanceNameplates;
import com.spxts.tdms.model.TransformerLrFields;
import com.spxts.tdms.model.TransformerNamePlateType;
import com.spxts.tdms.model.TransformerNewAdminData;
import com.spxts.tdms.model.TransformerTestAdminDetails;
import com.spxts.tdms.model.TransformerTestModel;
import com.spxts.tdms.model.TrasformerArrester;
import com.spxts.tdms.model.TrasformerArresters;
import com.spxts.tdms.model.TrasformerBushingNamePlate;
import com.spxts.tdms.model.TrasformerBushingNamePlates;
import com.spxts.tdms.model.TrasformerSurgeArresterTest;
import com.spxts.tdms.model.TrasformerSurgeArresterTestSet;
import com.spxts.tdms.model.WaukeshaTransformer;
import com.spxts.tdms.server.enitity.TransformerDTO;
import com.spxts.tdms.server.repository.UsersRepository;
import com.spxts.tdms.server.service.TransformerService;

@Service
public class TransformerServiceImpl implements TransformerService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${source.DTAX_SHARED_FOLDER}")
	private String dtaxSharedFolder;
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	public TransformerDTO getTransformerDetails(TransformerDTO transformerDTO) {
		return transformerDTO;
	}
	@Override
	public HashMap<String,WaukeshaTransformer> getDataModel(TransformerDTO transformerDTO) throws JAXBException, FileNotFoundException {
		HashMap<String,WaukeshaTransformer> resultMap = new HashMap<String,WaukeshaTransformer>();
		List<File> fileList  = getFile(transformerDTO.getId(),transformerDTO.getProcess());
		
		for(File file: fileList) {
			WaukeshaTransformer waukeshaTransformer = new WaukeshaTransformer();
			DataModelR2 dataModelR2 = (DataModelR2) convertXmlToObject(new FileInputStream(file));
			List<TransformerTestModel> tx = getWaukeshaTransformer(dataModelR2,file);
			TransformerNamePlateType tp = getDetails(dataModelR2); 
			String type = getTransformerType(dataModelR2);
			waukeshaTransformer.setTransformerDetails(tp);
			waukeshaTransformer.setTransformerSessions(tx);
			resultMap.put(file.getName(),waukeshaTransformer);
		}
	return resultMap;
	}
	
	
	private String getTransformerType(DataModelR2 dataModelR2) {
		
		String result = "";
		List<DtaSession> dtasession = dataModelR2.getDtaSessions().getDtaSession();
		int count = dtasession.size();
		if(count>0) {
			if(dtasession.get(0).getAutotransformerWithTertiary()!= null) {
				result = TransformerConstants.AUTO_TRANSFORMER_WITH_TERTIARY;
			}
			
			else if(dtasession.get(0).getTwoWindingTransformer() != null) {
				result = TransformerConstants.TWO_WINDING_TRANSFORMER;
			}
			
			else if(dtasession.get(0).getAutotransformerWithoutTertiary() != null) {
				result = TransformerConstants.AUTO_TRANSFORMER_WITH_TERTIARY;
			}
			
			else if(dtasession.get(0).getThreeWindingTransformer() != null) {
				result = TransformerConstants.THREE_WINDING_TRANSFORMER;
			}
		}
		return result;
	}
	
	
	//Method getting basic details & bushingNameplate details
	public TransformerNamePlateType getDetails(DataModelR2 dataModel) {
		
		List<BushingNameplate> bushingNamePlate = null;
		List<ArresterNameplate> arrNamePlate = null;
		
		LeakageReactanceNameplates leakageReactanceNameplate = null;
		
		TransformerNamePlateType transformerNamePlateType = new TransformerNamePlateType();
		
		TransformerTemplateType transformerTemplateType = getTransformerNameplateInstance(dataModel);
		
		bushingNamePlate = transformerTemplateType.getBushingNameplates().getBushingNameplate();
		arrNamePlate = transformerTemplateType.getArresterNameplates().getArresterNameplate();
		if(transformerTemplateType.getLeakageReactanceNameplates() != null) {
			leakageReactanceNameplate = transformerTemplateType.getLeakageReactanceNameplates();
		}
		
		transformerNamePlateType.setTransformerArresterNamePlates(getArresterNamePlate(arrNamePlate));
		transformerNamePlateType.setTransformerLeakageReactanceNameplates(getLeakageReactancePlateDetails(leakageReactanceNameplate));
		transformerNamePlateType.setTrasformerBushingNamePlates(getBushingNamePlateDetails(bushingNamePlate));
	
		transformerNamePlateType.setApparatustype(transformerTemplateType.getApparatusType());
		transformerNamePlateType.setBil(transformerTemplateType.getConfig());
		transformerNamePlateType.setSerialnum(transformerTemplateType.getSerialNum());
		
		transformerNamePlateType.setConfig(transformerTemplateType.getConfig());
		transformerNamePlateType.setCoolant(transformerTemplateType.getCoolant());
		transformerNamePlateType.setTanktype(transformerTemplateType.getTanktype());
		
		transformerNamePlateType.setWeightunits(transformerTemplateType.getWeightUnits());
		transformerNamePlateType.setPhases(transformerTemplateType.getPhases());
		transformerNamePlateType.setVolumeunits(transformerTemplateType.getVolumeUnits());
		transformerNamePlateType.setOilvolume(transformerTemplateType.getOilVolume());
		transformerNamePlateType.setSpecialid(transformerTemplateType.getSpecialId());
		
		transformerNamePlateType.setvA0(transformerTemplateType.getVa0());
		transformerNamePlateType.setvA1(transformerTemplateType.getVa1());
		transformerNamePlateType.setvA2(transformerTemplateType.getVa2());
	
		transformerNamePlateType.setkV0(transformerTemplateType.getKV0());
		transformerNamePlateType.setkV1(transformerTemplateType.getKV1());
		transformerNamePlateType.setkV2(transformerTemplateType.getKV2());
		transformerNamePlateType.setWeight(transformerTemplateType.getWeight());
		
		transformerNamePlateType.setMfr(transformerTemplateType.getMfr());
		transformerNamePlateType.setMfrlocation(transformerTemplateType.getMfrLocation());
		transformerNamePlateType.setYearmfg(transformerTemplateType.getYearMfg());
		
		return transformerNamePlateType;	
	}
	
	
	private TransformerTemplateType getTransformerNameplateInstance(DataModelR2 dataModel) {
		TransformerTemplateType transformerTemplateType = null;
		
		if(dataModel.getTwoWindingTransformerNameplate() != null) {
			transformerTemplateType = dataModel.getTwoWindingTransformerNameplate();
			
		}
		
		else if(dataModel.getAutotransformerNameplate() != null) {
			transformerTemplateType = dataModel.getAutotransformerNameplate();
			
		}
		
		else if(dataModel.getThreeWindingTransformerNameplate() != null) {
			transformerTemplateType = dataModel.getThreeWindingTransformerNameplate();
			
		}
		
		return transformerTemplateType;
	}
	
	
	private TrasformerBushingNamePlates getBushingNamePlateDetails(List<BushingNameplate> bushingNamePlate) {
		
		TrasformerBushingNamePlates transformerBushingNamePlates = new TrasformerBushingNamePlates();
		List<TrasformerBushingNamePlate> trasformerBushingNamePlate = new ArrayList<TrasformerBushingNamePlate>();
		for(BushingNameplate namePlate:bushingNamePlate) {
			TrasformerBushingNamePlate transformerBushing = new TrasformerBushingNamePlate();
			
	       	transformerBushing.setAmps(namePlate.getAmps());
	       	transformerBushing.setAutogenerated(namePlate.getAutogenerated());
	       	transformerBushing.setBil(namePlate.getBIL());
	       	transformerBushing.setBushingSerialNum(namePlate.getBushingSerialNum());
	       	transformerBushing.setBushingVoltage(namePlate.getBushingVoltage());
	       	transformerBushing.setC1Cap(namePlate.getC1Cap());
	       	
	       	transformerBushing.setC2Cap(namePlate.getC2Cap());
	        transformerBushing.setC2Pf(namePlate.getC2Pf());
	       	transformerBushing.setC1Pf(namePlate.getC1Pf());
	       	
	       	transformerBushing.setC2Cap(namePlate.getC2Pf());
	       	transformerBushing.setCatalogNum(namePlate.getCatalogNum());
	       	transformerBushing.setClazz(namePlate.getClazz());
	       	transformerBushing.setDrwg(namePlate.getDrwg());
	       	
	       	transformerBushing.setId(namePlate.getId());
	       	transformerBushing.setKv(namePlate.getKV());
	       	transformerBushing.setLocation(namePlate.getLocation());
	       	transformerBushing.setMfr(namePlate.getMfr());
	       	transformerBushing.setOther(namePlate.getOther());
	       	transformerBushing.setReplacementDateUtc(namePlate.getReplacementDateUtc());
	       	
	       	transformerBushing.setSoNum(namePlate.getSoNum());
	       	transformerBushing.setStatus(namePlate.getStatus());
			transformerBushing.setSoNum(namePlate.getSoNum());
			
			transformerBushing.setStatus(namePlate.getStatus());
			transformerBushing.setStyle(namePlate.getStyle());
			transformerBushing.setTap(namePlate.getTap());
			transformerBushing.setTerminationId(namePlate.getTerminationId());
			transformerBushing.setType(namePlate.getType());
			transformerBushing.setYearMfg(namePlate.getYearMfg());
			
			trasformerBushingNamePlate.add(transformerBushing);
		}		
		transformerBushingNamePlates.setTrasformerBushingNamePlate(trasformerBushingNamePlate);
		
		
		return transformerBushingNamePlates;
	}
	
	
	private TransformerLeakageReactanceNameplates getLeakageReactancePlateDetails(
			LeakageReactanceNameplates leakageReactanceNameplate) {
		
		TransformerLeakageReactanceNameplates plates = new TransformerLeakageReactanceNameplates();
		TransformerLeakageReactanceNameplate nameplate = new TransformerLeakageReactanceNameplate();
		
		if(leakageReactanceNameplate.getLeakageReactanceNameplate() != null) {
			nameplate.setDetcId(leakageReactanceNameplate.getLeakageReactanceNameplate().getDetcId());
			nameplate.setId(leakageReactanceNameplate.getLeakageReactanceNameplate().getId());
			nameplate.setLtcId(leakageReactanceNameplate.getLeakageReactanceNameplate().getLtcId());
			
			nameplate.setLeakageTcReferences(getLeakageTcRefs(leakageReactanceNameplate.getLeakageReactanceNameplate().getTcReferences()));
			nameplate.setTransformerLrFields(getLrfields(leakageReactanceNameplate.getLeakageReactanceNameplate().getLrFields()));
			plates.setTransformerLeakageReactanceNameplate(nameplate);
		}
		return plates;
	}
	
	
	private TransformerLrFields getLrfields(LrFields lrFields) {
		
		TransformerLrFields fields = new TransformerLrFields();
		
		fields.setBaseKV(lrFields.getBaseKV());
		fields.setBaseMVA(lrFields.getBaseMVA());
		fields.setDetcPosition(lrFields.getDetcPosition());
		fields.setImpedance(lrFields.getImpedance());
		fields.setLtcPosition(lrFields.getLtcPosition());
		
		return fields;
		
	}


	private LeakageTcReferences getLeakageTcRefs(TcReferences tcReferences) {
			
		LeakageTcReferences refs = new LeakageTcReferences();
		LeakageTcReferenceSet refset = new LeakageTcReferenceSet();
		LeakageTcReference ref = new LeakageTcReference();
		
		if(tcReferences != null) {
			ref.setTapchangerId(tcReferences.getTcReferenceSet().getTcReference().getTapchangerId());
			ref.setTapchangerPosition(tcReferences.getTcReferenceSet().getTcReference().getTapchangerPosition());
			ref.setTapchangerType(tcReferences.getTcReferenceSet().getTcReference().getTapchangerType());
		}
		refset.setLeakageTcReference(ref);
		refs.setLeakageTcReferenceSet(refset);
		
		return refs;
	}

	private TransformerArresterNamePlates getArresterNamePlate(List<ArresterNameplate> arrNamePlate) {
		// TODO Auto-generated method stub
		TransformerArresterNamePlates transformerArresterNamePlates = new TransformerArresterNamePlates();
		List<TransformerArresterNameplate> transformerArresterNameplate = new ArrayList<TransformerArresterNameplate>();
		
		for(ArresterNameplate namePlateItem:arrNamePlate) {
			TransformerArresterNameplate ArresterNameplate = new TransformerArresterNameplate();
			ArresterNameplate.setId(namePlateItem.getId());
			ArresterNameplate.setMfr(namePlateItem.getMfr());
			ArresterNameplate.setMiscData(namePlateItem.getMiscData());
			ArresterNameplate.setOverallCatalog(namePlateItem.getRatedKV());
			ArresterNameplate.setRatedKV(namePlateItem.getRatedKV());
			ArresterNameplate.setReplacementDateUtc(namePlateItem.getReplacementDateUtc());
			ArresterNameplate.setSaSerialNum(namePlateItem.getSaSerialNum());
			ArresterNameplate.setStackOrder(namePlateItem.getStackOrder());
			ArresterNameplate.setStatus(namePlateItem.getStatus());
			ArresterNameplate.setTestKV(namePlateItem.getTestKV());
			ArresterNameplate.setType(namePlateItem.getType());
			ArresterNameplate.setUnitCatalog(namePlateItem.getUnitCatalog());
			transformerArresterNameplate.add(ArresterNameplate);
		}
		
		transformerArresterNamePlates.setTransformerArresterNameplate(transformerArresterNameplate);
		return transformerArresterNamePlates;
	}
	
	
	//Method for getting Exciting current test details
	private List<ExcitingTestResults> getExcitationCurrentTestResults(List<ExcitingCurrentTest> excitationCurrentTest) {
		List<ExcitingTestResults> excitingCurrentTest = new ArrayList<ExcitingTestResults>();
		
		if(excitationCurrentTest != null) {
		for(ExcitingCurrentTest excitationCurrentTestItem:excitationCurrentTest) {
			ExcitingTestResults currentTestResult = new ExcitingTestResults();
			
			currentTestResult.setLtcId(excitationCurrentTestItem.getLtcId());
			currentTestResult.setDetc(excitationCurrentTestItem.getDetc());
			currentTestResult.setLtc(excitationCurrentTestItem.getLtc());
			currentTestResult.setDetcId(excitationCurrentTestItem.getDetcId());
			currentTestResult.setIncludeInPlot(excitationCurrentTestItem.getIncludeInPlot());
			currentTestResult.setIncludeInPlot(excitationCurrentTestItem.getIncludeInPlot());
			
			List<ExcitationIwcData> iwcList = new ArrayList<ExcitationIwcData>();
			List<IwcData> iwcdata = excitationCurrentTestItem.getIwcData();
			
			for(IwcData iwcItem:iwcdata) {
				ExcitationIwcData exIwcData = new ExcitationIwcData();
				
					exIwcData.setmMtrL(iwcItem.getMMtrL());
					exIwcData.setmMtrR(iwcItem.getMMtrR());
					exIwcData.setpMtrL(iwcItem.getPMtrL());
					exIwcData.setpMtrR(iwcItem.getPMtrR());
					exIwcData.setwMtrL(iwcItem.getWMtrL());
					exIwcData.setwMtrR(iwcItem.getWMtrR());
			
					iwcList.add(exIwcData);
				
			}
			currentTestResult.setIwcData(iwcList);

	
			if(excitationCurrentTestItem.getTcReferences() != null){
				if(excitationCurrentTestItem.getTcReferences().getTcReferenceSet()!= null) {
					List<TcReference> tcref = excitationCurrentTestItem.getTcReferences().getTcReferenceSet().getTcReference();
					List<ExitationTcReference> list  = new ArrayList<ExitationTcReference>();
						
					for(TcReference tcReference:tcref) {
						ExitationTcReference exTecReference = new ExitationTcReference();
						exTecReference.setTapchangerId(tcReference.getTapchangerId());
						exTecReference.setTapchangerPosition(tcReference.getTapchangerPosition());
						exTecReference.setTapchangerType(tcReference.getTapchangerType());
						list.add(exTecReference);
							//break;
					}
					ExcitationTcReferences tcrefs = new ExcitationTcReferences();
					ExitationTcReferenceSet tcset = new ExitationTcReferenceSet();
					tcrefs.setTcReferenceSet(tcset);
					tcset.setExitationTcReference(list);
					currentTestResult.setExcitationTcReferences(tcrefs);
				}
			currentTestResult.setExcitingCurrentFields(addExcitingCurrentFields(excitationCurrentTestItem.getExcitingCurrentFields()));	
			}
			
			
			List<LinefreqTestVoltages> lineFreqTestVol =excitationCurrentTestItem.getLinefreqTestVoltages();
			List<ExcitationLinefreqVoltages> excitationLineFreqVol = new ArrayList<ExcitationLinefreqVoltages>();
			
			for(LinefreqTestVoltages item:lineFreqTestVol) {
				ExcitationLinefreqVoltages exLineFrqVoltages = new ExcitationLinefreqVoltages();
					exLineFrqVoltages.setMdHighFreqkV(item.getMDHighFreqkV());
					exLineFrqVoltages.setMdLowFreqkV(item.getMDLowFreqkV());
					excitationLineFreqVol.add(exLineFrqVoltages);
			}
			currentTestResult.setLinefreqTestVoltages(excitationLineFreqVol);
			excitingCurrentTest.add(currentTestResult);
			
		}
		}
		return excitingCurrentTest;
	}
	
	
	//Method for getting Overall test details
	private List<OverallTestResults> getOverallTestResults(List<OverallTest> overallTest) {
		List<OverallTestResults> overallTestResults = new ArrayList<OverallTestResults>();
		
		for(OverallTest overallItem:overallTest) {
			OverallTestResults overallResults = new OverallTestResults();
			
				overallResults.setDateTestedUtc(overallItem.getDateTestedUtc());
				overallResults.setRatingExpertSystem(overallItem.getRatingExpertSystem());
				overallResults.setRatingTester(overallItem.getRatingTester());
				overallResults.setMessageExpertSystem(overallItem.getMessageExpertSystem());
				
				overallResults.setRequestedTestKV(overallItem.getRequestedTestKV());
				overallResults.setTestKV(overallItem.getTestKV());
				overallResults.setTestCircuit(overallItem.getTestCircuit());
				overallResults.setMa(overallItem.getMA());
				overallResults.setWatts(overallItem.getWatts());
				overallResults.setMeasuredCap(overallItem.getMeasuredCap());
				
				overallResults.setPfm(overallItem.getPfm());
				overallResults.setPfc(overallItem.getPfc());
				overallResults.setCorrectionFactor(overallItem.getCorrectionFactor());
				overallResults.setLineId(overallItem.getLineId());
				overallResults.setInsulation(overallItem.getInsulation());
				overallItem.getIwcData().getMMtrL();
				
				
				OverallIwcData overallIwcData = new OverallIwcData();
				
				overallIwcData.setmMtrL(overallItem.getIwcData().getMMtrL());
				overallIwcData.setmMtrR(overallItem.getIwcData().getMMtrR());
				overallIwcData.setpMtrL(overallItem.getIwcData().getPMtrL());
				overallIwcData.setpMtrR(overallItem.getIwcData().getPMtrR());
				overallIwcData.setwMtrL(overallItem.getIwcData().getWMtrL());
				overallIwcData.setwMtrR(overallItem.getIwcData().getWMtrR());
				
				overallResults.setIwcData(overallIwcData);
				overallTestResults.add(overallResults);
		}	
		
		return overallTestResults;
	}
	
	
	//Method for getting LvttRatio test details
	private SessionLvttRatioTestResults getLvttRatioTestResults(List<LvttratioTest> lvttrationList) {
		SessionLvttRatioTestResults lvttset = new SessionLvttRatioTestResults();
		
		List<SessionLvttRatioTest> lvttList = new ArrayList<SessionLvttRatioTest>();
		if(lvttrationList != null) {
			for(LvttratioTest item:lvttrationList) {
				SessionLvttRatioTest ratioTest = new SessionLvttRatioTest();
				
				SessionRatioTestFields ratioTestFields = new SessionRatioTestFields();
				SessionCablestates cableStates = new SessionCablestates();
				
				ratioTestFields.setAngle1(item.getRatioTestFields().getAngle1());
				ratioTestFields.setAngle2(item.getRatioTestFields().getAngle2());
				ratioTestFields.setAngle3(item.getRatioTestFields().getAngle3());
				
				ratioTestFields.setBenchmarkRatio(item.getRatioTestFields().getBenchmarkRatio());
				ratioTestFields.setBenchmarkRatioTriplePhase(item.getRatioTestFields().getBenchmarkRatioTriplePhase());
				ratioTestFields.setDateTestedUtc(item.getRatioTestFields().getDateTestedUtc());
				ratioTestFields.setDetcId(item.getRatioTestFields().getDetcId());
				
				ratioTestFields.setDeviation1(item.getRatioTestFields().getDeviation1());
				ratioTestFields.setDeviation2(item.getRatioTestFields().getDeviation2());
				ratioTestFields.setDeviation3(item.getRatioTestFields().getDeviation2());
				
				ratioTestFields.setHvMeasuredkv1(item.getRatioTestFields().getHvMeasuredkv1());
				ratioTestFields.setHvMeasuredkv2(item.getRatioTestFields().getHvMeasuredkv2());
				ratioTestFields.setHvMeasuredkv3(item.getRatioTestFields().getHvMeasuredkv3());
				
				ratioTestFields.setHvVolt(item.getRatioTestFields().getHvVolt());
				ratioTestFields.setLtcId(item.getRatioTestFields().getLtcId());
				
				ratioTestFields.setLvMeasuredkv1(item.getRatioTestFields().getLvMeasuredkv1());
				ratioTestFields.setLvMeasuredkv2(item.getRatioTestFields().getLvMeasuredkv2());
				ratioTestFields.setLvMeasuredkv3(item.getRatioTestFields().getLvMeasuredkv3());
				
				ratioTestFields.setLvVolt(item.getRatioTestFields().getLvVolt());
				ratioTestFields.setMaxLimit(item.getRatioTestFields().getMaxLimit());
				
				ratioTestFields.setMaxLimitTriplePhase(item.getRatioTestFields().getMaxLimitTriplePhase());
				ratioTestFields.setMessageExpertSystem(item.getRatioTestFields().getMessageExpertSystem());
				ratioTestFields.setMinLimit(item.getRatioTestFields().getMinLimit());
				ratioTestFields.setMinLimitTriplePhase(item.getRatioTestFields().getMinLimitTriplePhase());
				ratioTestFields.setRatingExpertSystem(item.getRatioTestFields().getRatingExpertSystem());
				ratioTestFields.setRatingTester(item.getRatioTestFields().getRatingTester());
				
				ratioTestFields.setRatio1(item.getRatioTestFields().getRatio1());
				ratioTestFields.setRatio2(item.getRatioTestFields().getRatio2());
				ratioTestFields.setRatio3(item.getRatioTestFields().getRatio3());
				
				ratioTestFields.setRequestedTestKV(item.getRatioTestFields().getRequestedTestKV());
				ratioTestFields.setTestCircuit(item.getRatioTestFields().getTestCircuit());
				
				ratioTestFields.setTriPhaseAngle1(item.getRatioTestFields().getTriPhaseAngle1());
				ratioTestFields.setTriPhaseAngle2(item.getRatioTestFields().getTriPhaseAngle2());
				ratioTestFields.setTriPhaseAngle3(item.getRatioTestFields().getTriPhaseAngle3());
				
				ratioTestFields.setTriPhaseDeviation1(item.getRatioTestFields().getTriPhaseDeviation1());
				ratioTestFields.setTriPhaseDeviation2(item.getRatioTestFields().getTriPhaseDeviation2());
				ratioTestFields.setTriPhaseDeviation3(item.getRatioTestFields().getTriPhaseDeviation3());
				
				ratioTestFields.setTriPhaseMessageExpertSystem(item.getRatioTestFields().getTriPhaseMessageExpertSystem());
				ratioTestFields.setTriPhaseRatingExpertSystem(item.getRatioTestFields().getTriPhaseRatingExpertSystem());
				ratioTestFields.setTriPhaseRatingTester(item.getRatioTestFields().getTriPhaseRatingTester());
				
				ratioTestFields.setTriPhaseRatio1(item.getRatioTestFields().getTriPhaseRatio1());
				ratioTestFields.setTriPhaseRatio2(item.getRatioTestFields().getTriPhaseRatio2());
				ratioTestFields.setTriPhaseRatio3(item.getRatioTestFields().getTriPhaseRatio3());
				ratioTestFields.setVanguardTestParameters(item.getRatioTestFields().getVanguardTestParameters());
				
				cableStates.setHv1(item.getCablestates().getHV1());
				cableStates.setHv2(item.getCablestates().getHV2());
				cableStates.setLv1(item.getCablestates().getLV1());
				cableStates.setLv2(item.getCablestates().getLV2());
				cableStates.setLvn(item.getCablestates().getLVN());
				
				cableStates.setM1(item.getCablestates().getM1());
				cableStates.setM2(item.getCablestates().getM2());
				cableStates.setM3(item.getCablestates().getM3());
				cableStates.setMg(item.getCablestates().getMG());
				
				ratioTest.setCablestates(cableStates);
				ratioTest.setSessionRatioTestFields(ratioTestFields);
				ratioTest.setLabel(item.getLabel());
				lvttList.add(ratioTest);
			}
		}
		lvttset.setSessionLvttRatioTest(lvttList);
		
		return lvttset;
	}
	
	
	//Method for getting AdminData details
	private SessionAdminDataDetails getAdminDataDetails(AdminDataType adminData) {
		SessionAdminDataDetails sessionAdminDataDetails = new SessionAdminDataDetails();
		
		sessionAdminDataDetails.setBottomSn(adminData.getBottomSn());
		sessionAdminDataDetails.setCheckDateUtc(adminData.getCheckDateUtc());
		sessionAdminDataDetails.setCheckedBy(adminData.getCheckedBy());
		sessionAdminDataDetails.setCopies(adminData.getCopies());
		
		sessionAdminDataDetails.setCounter1(adminData.getCounter1());
		sessionAdminDataDetails.setCounter2(adminData.getCounter2());
		sessionAdminDataDetails.setCounter3(adminData.getCounter3());
		sessionAdminDataDetails.setCrewSize(adminData.getCrewSize());
		sessionAdminDataDetails.setDtaVersion(adminData.getDtaVersion());
		sessionAdminDataDetails.setDuration(adminData.getDuration());
		
		sessionAdminDataDetails.setFactoryCalibrationDate(adminData.getFactoryCalibrationDate());;
		sessionAdminDataDetails.setFactoryRecalibrationDate(adminData.getFactoryRecalibrationDate());;
		sessionAdminDataDetails.setFieldCalibrationDate(adminData.getFieldCalibrationDate());
		sessionAdminDataDetails.setLineFrequency(adminData.getLineFrequency());
		sessionAdminDataDetails.setFirmwareVersion(adminData.getFirmwareVersion());
		sessionAdminDataDetails.setPoNum(adminData.getPoNum());
		
		sessionAdminDataDetails.setInsuranceBook(adminData.getInsuranceBook());
		sessionAdminDataDetails.setLastDateUtc(adminData.getLastDateUtc());
		sessionAdminDataDetails.setLastSheet(adminData.getLastSheet());
		sessionAdminDataDetails.setReason(adminData.getReason());
		
		sessionAdminDataDetails.setReasonEnum(adminData.getReasonEnum());
		sessionAdminDataDetails.setResonatorCounter(adminData.getResonatorCounter());
		sessionAdminDataDetails.setResonatorDateTestedUtc(adminData.getResonatorDateTestedUtc());
		sessionAdminDataDetails.setRetestDateUtc(adminData.getRetestDateUtc());
		sessionAdminDataDetails.setSheetNum(adminData.getSheetNum());
		
		sessionAdminDataDetails.setTestSetType(adminData.getTestSetType());
		sessionAdminDataDetails.setTestedBy(adminData.getTestedBy());
		sessionAdminDataDetails.setTopSn(adminData.getTopSn());
		sessionAdminDataDetails.setTravelTime(adminData.getTravelTime());
		sessionAdminDataDetails.setWo(adminData.getWo());
		
		return sessionAdminDataDetails;
	}
	
	
	//Method for getting Bushing designation details
	private BushingDesignationDetails getBushingDesignationDetails(List<Bushing> bushing) {
		BushingDesignationDetails bushingDesignationDetails = new BushingDesignationDetails();
		List<SessionsBushing> sessionBushingList = new ArrayList<SessionsBushing>();
		
		for(Bushing bushingItem:bushing) {
			SessionsBushing sessionsBushing = new SessionsBushing();
			sessionsBushing.setDesignation(bushingItem.getDesignation());
			sessionsBushing.setId(bushingItem.getId());
			sessionsBushing.setSerialNumber(bushingItem.getSerialNumber());
			sessionsBushing.setStatus(bushingItem.getStatus());
			sessionsBushing.setTerminationPhase(bushingItem.getTerminationPhase());

			sessionBushingList.add(sessionsBushing);
			bushingDesignationDetails.setSessionBushing(sessionBushingList);
		}
		
		return bushingDesignationDetails;
	}
	
	
	//Method for getting M7Bushing details
	private SessionM7BushingDetails getM7BushingDetails(List<M7BushingTest> dataModelM7Bushing) {
		
		SessionM7BushingDetails sessionM7BushingDetailsSet = new SessionM7BushingDetails();
		
		List<SessionM7BushingTest> sessionM7BushingTestList = new ArrayList<SessionM7BushingTest>();
		if(dataModelM7Bushing != null) {
			for(M7BushingTest listItem:dataModelM7Bushing) {
					SessionBushingTestResultSet sessionResultSet = new SessionBushingTestResultSet();
					SessionM7BushingTest sessionM7BushingTest = new SessionM7BushingTest();
					
					List<SessionBushingTestResult> sessionBushingTestResult = new ArrayList<SessionBushingTestResult>();
					List<BushingTestResults> bushingTestResultsList = listItem.getBushingTestResultsSet().getBushingTestResults();
					
					for(BushingTestResults item:bushingTestResultsList) {
						SessionBushingTestResult bushingTestResult = new SessionBushingTestResult();
						
						bushingTestResult.setBushingInsulation(item.getBushingInsulation());
						bushingTestResult.setCorrectionFactor(item.getCorrectionFactor());
						bushingTestResult.setDateTestedUtc(item.getDateTestedUtc());
						bushingTestResult.setMa(item.getMA());
						bushingTestResult.setMeasuredCap(item.getMeasuredCap());
						
						bushingTestResult.setMessageExpertSystem(item.getMessageExpertSystem());
						bushingTestResult.setPfc(item.getPfc());
						bushingTestResult.setPfm(item.getPfm());
						bushingTestResult.setRatingExpertSystem(item.getMessageExpertSystem());
						bushingTestResult.setRatingTester(item.getRatingTester());
						bushingTestResult.setRequestedTestKV(item.getRequestedTestKV());
						
						bushingTestResult.setTestCircuit(item.getTestCircuit());
						bushingTestResult.setTestKV(item.getTestKV());
						bushingTestResult.setWatts(item.getWatts());
						
						M7IwcData iwcdata = new M7IwcData();
						
						iwcdata.setmMtrL(item.getIwcData().getMMtrL());
						iwcdata.setmMtrR(item.getIwcData().getMMtrR());
						iwcdata.setpMtrL(item.getIwcData().getPMtrL());
						iwcdata.setpMtrR(item.getIwcData().getPMtrR());
						iwcdata.setwMtrL(item.getIwcData().getWMtrL());
						iwcdata.setwMtrR(item.getIwcData().getWMtrR());
						
						bushingTestResult.setM7iwcData(iwcdata);
						sessionBushingTestResult.add(bushingTestResult);
					}
					sessionResultSet.setSessionBushingTestResult(sessionBushingTestResult);
					sessionM7BushingTest.setBushingId(listItem.getBushingId());
					sessionM7BushingTest.setBushingTestResultSet(sessionResultSet);
					sessionM7BushingTestList.add(sessionM7BushingTest);	
			}
			sessionM7BushingDetailsSet.setM7BushingTest(sessionM7BushingTestList);
		}
		
		return sessionM7BushingDetailsSet;
	}
	
	
	//Method for getting excitation current field details
	private ExcitationExcitingCurrentFields addExcitingCurrentFields(ExcitingCurrentFields excitationCurrentTest) {
		ExcitationExcitingCurrentFields exfields = new ExcitationExcitingCurrentFields();
		
		exfields.setActualkv1(excitationCurrentTest.getActualkv1());
		exfields.setWindingTap(excitationCurrentTest.getWindingTap());
		
		exfields.setWindingId(excitationCurrentTest.getWindingId());
		exfields.setActualkv2(excitationCurrentTest.getActualkv2());
		exfields.setActualkv3(excitationCurrentTest.getActualkv3());
		
		exfields.setDateTestedUtc(excitationCurrentTest.getDateTestedUtc());
		exfields.setFrequencyPCT1(excitationCurrentTest.getFrequencyPCT1());
		exfields.setFrequencyPCT2(excitationCurrentTest.getFrequencyPCT2());
		exfields.setFrequencyPCT3(excitationCurrentTest.getFrequencyPCT3());
		
		exfields.setlOrC1(excitationCurrentTest.getLOrC1());
		exfields.setlOrC2(excitationCurrentTest.getLOrC2());
		exfields.setlOrC3(excitationCurrentTest.getLOrC3());
		
		exfields.setMa1(excitationCurrentTest.getMA1());
		exfields.setMa2(excitationCurrentTest.getMA2());
		exfields.setMa3(excitationCurrentTest.getMA3());
		
		exfields.setMessageExpertSystem(excitationCurrentTest.getMessageExpertSystem());
		exfields.setRatingExpertSystem(excitationCurrentTest.getRatingExpertSystem());
		
		exfields.setRatingTester(excitationCurrentTest.getRatingTester());
		exfields.setTestCircuit1(excitationCurrentTest.getTestCircuit1());
		exfields.setTestCircuit2(excitationCurrentTest.getTestCircuit2());
		exfields.setTestCircuit3(excitationCurrentTest.getTestCircuit3());
		
		exfields.setWatts1(excitationCurrentTest.getWatts1());
		exfields.setWatts2(excitationCurrentTest.getWatts2());
		exfields.setWatts3(excitationCurrentTest.getWatts3());
		exfields.setTestKV(excitationCurrentTest.getTestKV());
		
		return exfields;
	}
	
	public List<TransformerTestModel> getWaukeshaTransformer(DataModelR2 dataModel,File file){
		List<TransformerTestModel> transformer = new ArrayList<TransformerTestModel>();
		transformer = copySessionfromDTASession(dataModel, transformer,file);

		return transformer;

	}

	
	private List<TransformerTestModel> copySessionfromDTASession(DataModelR2 dataModel, List<TransformerTestModel> transformer,File file) {
		List<DtaSession> dtasession = dataModel.getDtaSessions().getDtaSession();
		int count = dtasession.size();
		for(int i=0;i<count;i++) {
			TransformerDtasessionBasicDetails td = new TransformerDtasessionBasicDetails();
			
			 td.setCctDesignation(dtasession.get(i).getCctDesignation());
		     td.setCompany(dtasession.get(i).getCompany());
		     td.setCurrentTimezone(dtasession.get(i).getCurrentTimezone());
		     td.setDivision(dtasession.get(i).getDivision());
		     td.setFirstTestDateUtc(dtasession.get(i).getFirstTestDateUtc());
		     
		     td.setLastTestDateUtc(dtasession.get(i).getLastTestDateUtc());
		     td.setLocation(dtasession.get(i).getLocation());
		     td.setSessionCreatedDateUtc(dtasession.get(i).getSessionCreatedDateUtc());
		     td.setSessionNote(dtasession.get(i).getSessionNote());
		     td.setUtcOffsetMinutes(dtasession.get(i).getUtcOffsetMinutes());	
		     
			TransformerTestModel transformerModel = new TransformerTestModel();
			TransformerTestModel auto = getOverAllTestsForTransformer(transformerModel,dtasession.get(i),file);
			transformerModel.setTransformerDtasessionBasicDetails(td);
			
			transformer.add(auto);
		}
	 
		  return transformer;
	}
	
	//Method for getting All test plan details
	private TransformerTestModel getOverAllTestsForTransformer(TransformerTestModel transformerModel,DtaSession ds,File file) {
			List<M7BushingTest> DataModelM7Bushing = null;
			List<OverallTest> overallTest = null;
			AdminDataType adminData = null;
			
			List<AdminDataType> newAdminData = null;
			
			List<Bushing> bushing = null;
			List<ExcitingCurrentTest> excitationCurrentTest = null;
			List<LvttratioTest> lvttrationList = null;
			
			List<Arrester> arrester = null;
			
			List<SurgeArresterTest> surgeArrTest = null;
			
			TestConditions testConditions = null;
			
			TransformerType transformerType  = getTranformerTypeInstance(ds);
			
			testConditions = transformerType.getTestConditions();
			
			overallTest = transformerType.getOverallTestSet().getOverallTest();
			
			if(transformerType.getExcitingCurrentTestSet() != null) {
				excitationCurrentTest = transformerType.getExcitingCurrentTestSet().getExcitingCurrentTest();
			}
			
			adminData = transformerType.getAdminData();
	
			bushing = transformerType.getBushingDesignations().getBushing();
			
			if(transformerType.getM7BushingTestSet() != null) {
				DataModelM7Bushing = transformerType.getM7BushingTestSet().getM7BushingTest();
			}
			
			if(transformerType.getLvttratioTestSet() != null) {
				lvttrationList = transformerType.getLvttratioTestSet().getLvttratioTest();
			}
			
			if(transformerType.getArresters() != null) {
				arrester = transformerType.getArresters().getArrester();
			}
			
			if(transformerType.getSurgeArresterTestSet() != null) {
				surgeArrTest = transformerType.getSurgeArresterTestSet().getSurgeArresterTest();
			}
			
			if(transformerType.getTestAdminData() != null) {
				newAdminData = transformerType.getTestAdminData().getAdminData();
			}
			
			transformerModel.setTransformerTestAdminDetails(getTestAdminDataDetails(newAdminData));
			
			transformerModel.setTrasformerSurgeArresterTestSet(getSurgeArresterTestDetails(surgeArrTest));
			transformerModel.setTrasformerArresters(getTransformerArresterDetails(arrester));
			transformerModel.setSessionTestConditions(getSessionTestConditionsDetails(testConditions));
			transformerModel.setM7BushingDetails(getM7BushingDetails(DataModelM7Bushing));
			transformerModel.setBushingDesignationDetails(getBushingDesignationDetails(bushing));
			transformerModel.setSessionAdminDataDetails(getAdminDataDetails(adminData));
			transformerModel.setSessionLvttRatioTestResults(getLvttRatioTestResults(lvttrationList));
			transformerModel.setOverallTest(getOverallTestResults(overallTest));
			transformerModel.setExcitingTestResults(getExcitationCurrentTestResults(excitationCurrentTest));
			
			return transformerModel;
	}		
	

	
	private TransformerTestAdminDetails getTestAdminDataDetails(List<AdminDataType> newAdminData) {
		TransformerTestAdminDetails transformerTestAdminDetails = new TransformerTestAdminDetails();
		 List<TransformerNewAdminData> transformerNewAdminData = new  ArrayList<TransformerNewAdminData>();
		 if(newAdminData != null) {
		 for(AdminDataType adminData:newAdminData) {
			 TransformerNewAdminData adminDataDetails = new TransformerNewAdminData();
			 
			 adminDataDetails.setBottomSn(adminData.getBottomSn());
			 adminDataDetails.setCheckDateUtc(adminData.getCheckDateUtc());
			 adminDataDetails.setCheckedBy(adminData.getCheckedBy());
			 adminDataDetails.setCopies(adminData.getCopies());
				
			 adminDataDetails.setCounter1(adminData.getCounter1());
			 adminDataDetails.setCounter2(adminData.getCounter2());
			 adminDataDetails.setCounter3(adminData.getCounter3());
			 adminDataDetails.setCrewSize(adminData.getCrewSize());
			 adminDataDetails.setDtaVersion(adminData.getDtaVersion());
			 adminDataDetails.setDuration(adminData.getDuration());
				
			 adminDataDetails.setFactoryCalibrationDate(adminData.getFactoryCalibrationDate());;
			 adminDataDetails.setFactoryRecalibrationDate(adminData.getFactoryRecalibrationDate());;
			 adminDataDetails.setFieldCalibrationDate(adminData.getFieldCalibrationDate());
			 adminDataDetails.setLineFrequency(adminData.getLineFrequency());
			 adminDataDetails.setFirmwareVersion(adminData.getFirmwareVersion());
			 adminDataDetails.setPoNum(adminData.getPoNum());
				
			 adminDataDetails.setInsuranceBook(adminData.getInsuranceBook());
			 adminDataDetails.setLastDateUtc(adminData.getLastDateUtc());
			 adminDataDetails.setLastSheet(adminData.getLastSheet());
			 adminDataDetails.setReason(adminData.getReason());
				
			 adminDataDetails.setReasonEnum(adminData.getReasonEnum());
			 adminDataDetails.setResonatorCounter(adminData.getResonatorCounter());
			 adminDataDetails.setResonatorDateTestedUtc(adminData.getResonatorDateTestedUtc());
			 adminDataDetails.setRetestDateUtc(adminData.getRetestDateUtc());
			 adminDataDetails.setSheetNum(adminData.getSheetNum());
				
			 adminDataDetails.setTestSetType(adminData.getTestSetType());
			 adminDataDetails.setTestedBy(adminData.getTestedBy());
			 adminDataDetails.setTopSn(adminData.getTopSn());
			 adminDataDetails.setTravelTime(adminData.getTravelTime());
			 adminDataDetails.setWo(adminData.getWo());
			 
			 transformerNewAdminData.add(adminDataDetails);
		 }
		 }
		 transformerTestAdminDetails.setTransformerNewAdminData(transformerNewAdminData);
		return transformerTestAdminDetails;
	}
	private TrasformerSurgeArresterTestSet getSurgeArresterTestDetails(List<SurgeArresterTest> surgeArrTest) {
		TrasformerSurgeArresterTestSet trasformerSurgeArresterTestSet = new TrasformerSurgeArresterTestSet();
		List<TrasformerSurgeArresterTest> trasformerSurgeArresterTest = new ArrayList<TrasformerSurgeArresterTest>();
		
		 for(SurgeArresterTest surgeArrester:surgeArrTest) {
			 TrasformerSurgeArresterTest dtaSurgeArresterTest = new TrasformerSurgeArresterTest();
			 
			 dtaSurgeArresterTest.setArresterId(surgeArrester.getArresterId());
			 dtaSurgeArresterTest.setCircuitDescription(surgeArrester.getCircuitDescription());
			 dtaSurgeArresterTest.setDateTestedUtc(surgeArrester.getDateTestedUtc());
			 dtaSurgeArresterTest.setDualM7CircuitDescription(surgeArrester.getDualM7CircuitDescription());
			 dtaSurgeArresterTest.setMa(surgeArrester.getMA());
			 dtaSurgeArresterTest.setMessageExpertSystem(surgeArrester.getMessageExpertSystem());
			 dtaSurgeArresterTest.setRatingExpertSystem(surgeArrester.getRatingExpertSystem());
			 dtaSurgeArresterTest.setRatingTester(surgeArrester.getRatingExpertSystem());
			 dtaSurgeArresterTest.setRequestedTestKV(surgeArrester.getRequestedTestKV());
			 dtaSurgeArresterTest.setSaSerialNum(surgeArrester.getSaSerialNum());
			 dtaSurgeArresterTest.setSingleM7CircuitDescription(surgeArrester.getSingleM7CircuitDescription());
			 dtaSurgeArresterTest.setTestCircuit(surgeArrester.getTestCircuit());
			 dtaSurgeArresterTest.setTestKV(surgeArrester.getTestKV());
			 dtaSurgeArresterTest.setWatts(surgeArrester.getWatts());
			 
			 ArresterIwcData arresterIwcData=new ArresterIwcData();
			 
			 arresterIwcData.setmMtrL(surgeArrester.getIwcData().getMMtrL());
			 arresterIwcData.setmMtrR(surgeArrester.getIwcData().getMMtrR());
			 arresterIwcData.setpMtrL(surgeArrester.getIwcData().getPMtrL());
			 arresterIwcData.setpMtrR(surgeArrester.getIwcData().getPMtrR());
			 arresterIwcData.setwMtrL(surgeArrester.getIwcData().getWMtrL());
			 arresterIwcData.setwMtrR(surgeArrester.getIwcData().getWMtrR());
			 
			 dtaSurgeArresterTest.setArresterIwcData(arresterIwcData);
			 trasformerSurgeArresterTest.add(dtaSurgeArresterTest);
		 }
			
		trasformerSurgeArresterTestSet.setTrasformerSurgeArresterTest(trasformerSurgeArresterTest);
		
		return trasformerSurgeArresterTestSet;
	}
	
	
	private TrasformerArresters getTransformerArresterDetails(List<Arrester> arrester) {
		TrasformerArresters trasformerArresters = new TrasformerArresters();
		List<TrasformerArrester> trasformerArrester = new ArrayList<TrasformerArrester>();
		
		for(Arrester arr:arrester) {
		     
			TrasformerArrester tArrester = new TrasformerArrester();

			tArrester.setId(arr.getId());
			tArrester.setSaLocation(arr.getSaLocation());
			tArrester.setSaSerialNum(arr.getSaSerialNum());
			tArrester.setSaStackorder(arr.getSaStackorder());
			tArrester.setStatus(arr.getStatus());
			
			trasformerArrester.add(tArrester);
		}
		trasformerArresters.setTrasformerArrester(trasformerArrester);
		return trasformerArresters;
	}
	
	
	private SessionTestConditions getSessionTestConditionsDetails(TestConditions testConditions) {
		SessionTestConditions sessionTestConditions = new SessionTestConditions();
		sessionTestConditions.setAirTemp(testConditions.getAirTemp());
		sessionTestConditions.setHaveOverallCf(testConditions.getHaveOverallCf());
		sessionTestConditions.setHumidity(testConditions.getHumidity());
		sessionTestConditions.setInternalTemp(testConditions.getInternalTemp());
		sessionTestConditions.setOverallCf(testConditions.getOverallCf());
		sessionTestConditions.setWeather(testConditions.getWeather());
		return sessionTestConditions;
	}
	
	
	private TransformerType getTranformerTypeInstance(DtaSession ds) {
		TransformerType transformerType = null;
		if(ds.getAutotransformerWithTertiary()!= null) {
			transformerType =  ds.getAutotransformerWithTertiary();
		}
		
		else if(ds.getTwoWindingTransformer() != null) {
			transformerType =  ds.getTwoWindingTransformer();
		}
		
		else if(ds.getAutotransformerWithoutTertiary() != null) {
			transformerType =  ds.getAutotransformerWithoutTertiary();
		}
		
		else if(ds.getThreeWindingTransformer() != null) {
			transformerType =  ds.getThreeWindingTransformer();
		}
		return transformerType;
	}
	
	
	private static Object convertXmlToObject(InputStream in) throws JAXBException {
		JAXBContext jaxbcontext = JAXBContext.newInstance(DataModelR2.class);
		Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
		
		return (DataModelR2) unmarshaller.unmarshal(in);	
	}
	
	
	private List<File> getFile(String transformerId, String process) {
		List<File> file = getOutputProcessFile(transformerId,".dtax");
		return file;
	}

	
	public List<File> getOutputProcessFile(String transformerId,String extension) {
		List<File> fileList = new ArrayList<File>();
		File directoryPath = new File(dtaxSharedFolder);
//		System.out.println(directoryPath.getAbsolutePath()+"Folder ## "+dtaxSharedFolder);
//		System.out.println("Folder ## "+dtaxSharedFolder);
		String folders[] = directoryPath.list();
		for (int i = 0; i < folders.length; i++) {
//			System.out.println("    single Folder ## "+folders[i]);
			if(folders[i].toString().equals(transformerId)) {
				File filesList = new File(directoryPath+"/"+transformerId);
//				System.out.println("        File ## "+directoryPath+"/"+transformerId);
				String files[] = filesList.list();
				for (int j = 0; j < files.length; j++) {
//					System.out.println("            single File ## "+files[j]);
					if(files[j].toString().startsWith(transformerId) && files[j].toString().endsWith(extension)) {
//						System.out.println(files[j].toString());
						fileList.add(new File(directoryPath+"/"+transformerId+"/"+files[j].toString()));
					}
				}
			}
		}
		return fileList;
	}
}
