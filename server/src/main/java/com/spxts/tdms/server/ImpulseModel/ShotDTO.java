package com.spxts.tdms.server.ImpulseModel;

public class ShotDTO {
	
	private int number;
	private String filename;
	private BushingTerminalsDTO iBushingTerminals;
	private TransformerTapsDTO iTransformerTaps;
	
	private TieTogetherDTO itieTogether;
	private float dividerMultiplier;
	private TestResultsDTO iTestResults;
	private int impulseGenSetupNumber;
	
	private GeneratorPressureSettingsDTO generatorPressureSettings;
	private ChopTowerPressureSettingsDTO chopTowerPressureSettings;
	
	private int grounds;
	private int chopStage;
	private String comments;
	
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public BushingTerminalsDTO getiBushingTerminals() {
		return iBushingTerminals;
	}
	public void setiBushingTerminals(BushingTerminalsDTO iBushingTerminals) {
		this.iBushingTerminals = iBushingTerminals;
	}
	public TransformerTapsDTO getiTransformerTaps() {
		return iTransformerTaps;
	}
	public void setiTransformerTaps(TransformerTapsDTO iTransformerTaps) {
		this.iTransformerTaps = iTransformerTaps;
	}
	public TestResultsDTO getiTestResults() {
		return iTestResults;
	}
	public void setiTestResults(TestResultsDTO iTestResults) {
		this.iTestResults = iTestResults;
	}
	public TieTogetherDTO getItieTogether() {
		return itieTogether;
	}
	public void setItieTogether(TieTogetherDTO itieTogether) {
		this.itieTogether = itieTogether;
	}
	public int getImpulseGenSetupNumber() {
		return impulseGenSetupNumber;
	}
	public void setImpulseGenSetupNumber(int impulseGenSetupNumber) {
		this.impulseGenSetupNumber = impulseGenSetupNumber;
	}
	public int getGrounds() {
		return grounds;
	}
	public void setGrounds(int grounds) {
		this.grounds = grounds;
	}
	public int getChopStage() {
		return chopStage;
	}
	public void setChopStage(int chopStage) {
		this.chopStage = chopStage;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public float getDividerMultiplier() {
		return dividerMultiplier;
	}
	public void setDividerMultiplier(float dividerMultiplier) {
		this.dividerMultiplier = dividerMultiplier;
	}
	public GeneratorPressureSettingsDTO getGeneratorPressureSettings() {
		return generatorPressureSettings;
	}
	public void setGeneratorPressureSettings(GeneratorPressureSettingsDTO generatorPressureSettings) {
		this.generatorPressureSettings = generatorPressureSettings;
	}
	public ChopTowerPressureSettingsDTO getChopTowerPressureSettings() {
		return chopTowerPressureSettings;
	}
	public void setChopTowerPressureSettings(ChopTowerPressureSettingsDTO chopTowerPressureSettings) {
		this.chopTowerPressureSettings = chopTowerPressureSettings;
	}
	
	
	

}
