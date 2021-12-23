package com.spxts.tdms.model;

public class DTASession {
	private TwoWindingTransformer twoWindingTransformer;
	private TransformerTestModel transformerTestModel;

    public TwoWindingTransformer getTwoWindingTransformer() {
	return twoWindingTransformer;
	}

	public void setTwoWindingTransformer(TwoWindingTransformer twoWindingTransformer) {
		this.twoWindingTransformer = twoWindingTransformer;
	}

	public TransformerTestModel getAutotransformerWithTertiary() {
		return transformerTestModel;
	}

	public void setAutotransformerWithTertiary(TransformerTestModel autotransformerWithTertiary) {
		this.transformerTestModel = autotransformerWithTertiary;
	}
}
