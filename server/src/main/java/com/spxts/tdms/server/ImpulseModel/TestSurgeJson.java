package com.spxts.tdms.server.ImpulseModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spxts.tdms.server.service.impl.DBTAndDMXMFileReadServiceImpl;

public class TestSurgeJson {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Print");
		DBTAndDMXMFileReadServiceImpl db = new DBTAndDMXMFileReadServiceImpl();
		
		
		ImpulseSurgeLogDTO iSurgeLog=	(new TestSurgeJson()).createSampleSurgeLog("WT-05047");
		
		db.createDBTJSON(iSurgeLog);
		
	//File file = new File("C:\\Work\\Working\\Tranformers\\WT-05561 MEAS\\WT-05561 MEAS\\NewName.dbf");
	//	db.uploadDBTDataFile(file);
		
		db.getDBTJSON("WT-05050");
		

	}
	
	public ImpulseSurgeLogDTO createSampleSurgeLog(String transformerId) {
		ImpulseSurgeLogDTO impulseSurgelog = new ImpulseSurgeLogDTO();
		impulseSurgelog.setCustomername("ALABAMA");
		impulseSurgelog.setUnit(transformerId);
		impulseSurgelog.setTest("ANSI");
		impulseSurgelog.setTech("/2946");
		impulseSurgelog.setDate("6/3/2020");
		impulseSurgelog.setTest_floor("STF");
		
		BILinformationDTO bilDTO = new BILinformationDTO();
		
		LineinfoDTO ldtohv = new LineinfoDTO();
		ldtohv.setFw(450);
		ldtohv.setCw(495);
		ldtohv.setSwss(0);
		HVLineDTO HVLDto = new HVLineDTO();HVLDto.setLineinfoDTO(ldtohv);
		bilDTO.setHvLine(HVLDto);
		
		LineinfoDTO ldtolv = new LineinfoDTO();
		ldtolv.setFw(250);
		ldtolv.setCw(275);
		ldtolv.setSwss(0);
		LVLineDTO LVLDto = new LVLineDTO();LVLDto.setLineinfoDTO(ldtolv);
		bilDTO.setLvLine(LVLDto);
		
		LineinfoDTO ldtotv = new LineinfoDTO();
		ldtotv.setFw(110);
		ldtotv.setCw(120);
		ldtotv.setSwss(0);
		TVLineDTO TVLDto = new TVLineDTO();TVLDto.setLineinfoDTO(ldtotv);
		bilDTO.setTvLine(TVLDto);
		
		LineinfoDTO ldtonuetral = new LineinfoDTO();
		ldtonuetral.setHo(450);
		ldtonuetral.setXo(120);
		ldtonuetral.setYo(0);
		NeutralDTO neutralDTO = new NeutralDTO();neutralDTO.setLineinfoDTO(ldtonuetral);
		bilDTO.setNeutral(neutralDTO);
		
		impulseSurgelog.setBilDto(bilDTO);
		
		
		ImpulseGeneratorSetupDTO impulseGenSetupDTO1 = new ImpulseGeneratorSetupDTO();
		impulseGenSetupDTO1.setNumber(1);
		impulseGenSetupDTO1.setStagesinSeries(1);
		impulseGenSetupDTO1.setStagesinParallel(10);
		IGenSetupResDTO genResDTFO1 = new IGenSetupResDTO();
		genResDTFO1.setrCount(5);
		genResDTFO1.setrValue(5);		
		IGenSetupResDTO genResDTTO1 = new IGenSetupResDTO();
		genResDTTO1.setrCount(4);
		genResDTTO1.setrValue(6);
		IGenSetupResDTO genResDTTO2 = new IGenSetupResDTO();
		genResDTTO2.setrCount(6);
		genResDTTO2.setrValue(150);
		
		List<IGenSetupResDTO> listgenResFrontDTO = new ArrayList();
		listgenResFrontDTO.add(genResDTFO1);
		impulseGenSetupDTO1.setROneFrontCount(listgenResFrontDTO.size());
		impulseGenSetupDTO1.setROneFront(listgenResFrontDTO);
		
		List<IGenSetupResDTO> listgenResTailDTO = new ArrayList();
		listgenResTailDTO.add(genResDTTO1);
		listgenResTailDTO.add(genResDTTO2);
		impulseGenSetupDTO1.setROneTailCount(listgenResTailDTO.size());
		impulseGenSetupDTO1.setROneTail(listgenResTailDTO);
		
		
		ImpulseGeneratorSetupDTO impulseGenSetupDTO2 = new ImpulseGeneratorSetupDTO();
		impulseGenSetupDTO2.setNumber(2);
		impulseGenSetupDTO2.setStagesinSeries(1);
		impulseGenSetupDTO2.setStagesinParallel(10);
		IGenSetupResDTO genResDTFO2 = new IGenSetupResDTO();
		genResDTFO2.setrCount(5);
		genResDTFO2.setrValue(22);		
		IGenSetupResDTO genResDTTO3 = new IGenSetupResDTO();
		genResDTTO3.setrCount(7);
		genResDTTO3.setrValue(6);
		IGenSetupResDTO genResDTTO4 = new IGenSetupResDTO();
		genResDTTO4.setrCount(3);
		genResDTTO4.setrValue(150);
		
		List<IGenSetupResDTO> listgenResFrontDTO2 = new ArrayList();
		listgenResFrontDTO2.add(genResDTFO2);
		impulseGenSetupDTO2.setROneFrontCount(listgenResFrontDTO2.size());
		impulseGenSetupDTO2.setROneFront(listgenResFrontDTO2);
		
		List<IGenSetupResDTO> listgenResTailDTO2 = new ArrayList();
		listgenResTailDTO2.add(genResDTTO3);
		listgenResTailDTO2.add(genResDTTO4);
		impulseGenSetupDTO2.setROneTailCount(listgenResTailDTO2.size());
		impulseGenSetupDTO2.setROneTail(listgenResTailDTO2);
		
		List<ImpulseGeneratorSetupDTO> listImpulseGeneratorSetupDTO = new ArrayList();
		listImpulseGeneratorSetupDTO.add(impulseGenSetupDTO1);
		listImpulseGeneratorSetupDTO.add(impulseGenSetupDTO2);
		
		impulseSurgelog.setImpulseGenSetupDTO(listImpulseGeneratorSetupDTO);
		
		impulseSurgelog.setBase_mva(24);
		impulseSurgelog.setNamePlateDesignImpedance(8.90f);
		
		ShotDTO sDto = new ShotDTO();
		sDto.setNumber(327);
		sDto.setFilename("H0RFW1");
		BushingTerminalsDTO bushTerminalsDTO = new BushingTerminalsDTO();
		bushTerminalsDTO.setSurge("HO");
		bushTerminalsDTO.setGround("X'S");
		bushTerminalsDTO.setCurrent("H1,2,3");
		sDto.setiBushingTerminals(bushTerminalsDTO);
		TransformerTapsDTO tdto = new TransformerTapsDTO();
		tdto.setHVTap("E");
		tdto.setLVTap("16R");
		sDto.setiTransformerTaps(tdto);
		TieTogetherDTO ttDto = new TieTogetherDTO();
		ttDto.setValue1("R");
		ttDto.setValue2("U");
		sDto.setItieTogether(ttDto);
		sDto.setDividerMultiplier(635.8f);
		//sDto.set
		TransformerTapsDTO transformerTapDTO = new TransformerTapsDTO();
		transformerTapDTO.setHVTap("E");
		transformerTapDTO.setLVTap("16R");
		TestResultsDTO testResultsDTO = new TestResultsDTO();
		testResultsDTO.setKv_peak(232.7f);
		testResultsDTO.setTimetopeak_t1(1.3f);
		testResultsDTO.setTailtime(50.8f);
		testResultsDTO.setTimetoflashover(0f);
		testResultsDTO.setRateofrise(0f);
		testResultsDTO.setTimetopeak_tp(0f);
		testResultsDTO.setTimeaboveninty(0f);
		testResultsDTO.setTimetozero(0f);
		testResultsDTO.setRodgap(0f);
		testResultsDTO.setFiringvoltage(45);
		sDto.setiTestResults(testResultsDTO);
		
		GeneratorPressureSettingsDTO gtnSettings = new GeneratorPressureSettingsDTO();
		gtnSettings.setGtnAutoManual("Auto");
		ChopTowerPressureSettingsDTO imcSettings = new ChopTowerPressureSettingsDTO();
		sDto.setGeneratorPressureSettings(gtnSettings);
		sDto.setChopTowerPressureSettings(imcSettings);
		
		
		sDto.setImpulseGenSetupNumber(4);
		sDto.setGrounds(2);
		sDto.setChopStage(6);
		sDto.setComments("HV CT 1 PASS");
		List sDtoList = new ArrayList();
		sDtoList.add(sDto);
		impulseSurgelog.setShotInfo(sDtoList);
		
		return impulseSurgelog;
		
	}

}
