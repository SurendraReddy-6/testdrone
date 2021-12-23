//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.12.15 at 01:24:59 PM IST 
//


package com.spxts.tdms.dobleUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for admin-data-type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="admin-data-type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="copies" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="top_sn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tested-by" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="retest-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="check-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="checked-by" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="wo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bottom-sn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="po-num" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="insurance-book" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="travel-time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="last-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="last-sheet" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="test-set-type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reason" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reason-enum" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sheet-num" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="crew-size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="counter-1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="counter-2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="counter-3" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resonator-counter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resonator-date-tested-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="factory-calibration-date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="factory-recalibration-date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="field-calibration-date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="LineFrequency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firmware-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dta-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "admin-data-type")
public class AdminDataType {

    @XmlAttribute(name = "copies")
    protected String copies;
    @XmlAttribute(name = "top_sn")
    protected String topSn;
    @XmlAttribute(name = "tested-by")
    protected String testedBy;
    @XmlAttribute(name = "retest-date-utc")
    protected String retestDateUtc;
    @XmlAttribute(name = "check-date-utc")
    protected String checkDateUtc;
    @XmlAttribute(name = "checked-by")
    protected String checkedBy;
    @XmlAttribute(name = "wo")
    protected String wo;
    @XmlAttribute(name = "bottom-sn")
    protected String bottomSn;
    @XmlAttribute(name = "po-num")
    protected String poNum;
    @XmlAttribute(name = "insurance-book")
    protected String insuranceBook;
    @XmlAttribute(name = "travel-time")
    protected String travelTime;
    @XmlAttribute(name = "duration")
    protected String duration;
    @XmlAttribute(name = "last-date-utc")
    protected String lastDateUtc;
    @XmlAttribute(name = "last-sheet")
    protected String lastSheet;
    @XmlAttribute(name = "test-set-type")
    protected String testSetType;
    @XmlAttribute(name = "reason")
    protected String reason;
    @XmlAttribute(name = "reason-enum")
    protected String reasonEnum;
    @XmlAttribute(name = "sheet-num")
    protected String sheetNum;
    @XmlAttribute(name = "crew-size")
    protected String crewSize;
    @XmlAttribute(name = "counter-1")
    protected String counter1;
    @XmlAttribute(name = "counter-2")
    protected String counter2;
    @XmlAttribute(name = "counter-3")
    protected String counter3;
    @XmlAttribute(name = "resonator-counter")
    protected String resonatorCounter;
    @XmlAttribute(name = "resonator-date-tested-utc")
    protected String resonatorDateTestedUtc;
    @XmlAttribute(name = "factory-calibration-date")
    protected String factoryCalibrationDate;
    @XmlAttribute(name = "factory-recalibration-date")
    protected String factoryRecalibrationDate;
    @XmlAttribute(name = "field-calibration-date")
    protected String fieldCalibrationDate;
    @XmlAttribute(name = "LineFrequency")
    protected String lineFrequency;
    @XmlAttribute(name = "firmware-version")
    protected String firmwareVersion;
    @XmlAttribute(name = "dta-version")
    protected String dtaVersion;

    /**
     * Gets the value of the copies property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopies() {
        return copies;
    }

    /**
     * Sets the value of the copies property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopies(String value) {
        this.copies = value;
    }

    /**
     * Gets the value of the topSn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopSn() {
        return topSn;
    }

    /**
     * Sets the value of the topSn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopSn(String value) {
        this.topSn = value;
    }

    /**
     * Gets the value of the testedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestedBy() {
        return testedBy;
    }

    /**
     * Sets the value of the testedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestedBy(String value) {
        this.testedBy = value;
    }

    /**
     * Gets the value of the retestDateUtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetestDateUtc() {
        return retestDateUtc;
    }

    /**
     * Sets the value of the retestDateUtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetestDateUtc(String value) {
        this.retestDateUtc = value;
    }

    /**
     * Gets the value of the checkDateUtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckDateUtc() {
        return checkDateUtc;
    }

    /**
     * Sets the value of the checkDateUtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckDateUtc(String value) {
        this.checkDateUtc = value;
    }

    /**
     * Gets the value of the checkedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckedBy() {
        return checkedBy;
    }

    /**
     * Sets the value of the checkedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckedBy(String value) {
        this.checkedBy = value;
    }

    /**
     * Gets the value of the wo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWo() {
        return wo;
    }

    /**
     * Sets the value of the wo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWo(String value) {
        this.wo = value;
    }

    /**
     * Gets the value of the bottomSn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBottomSn() {
        return bottomSn;
    }

    /**
     * Sets the value of the bottomSn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBottomSn(String value) {
        this.bottomSn = value;
    }

    /**
     * Gets the value of the poNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoNum() {
        return poNum;
    }

    /**
     * Sets the value of the poNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoNum(String value) {
        this.poNum = value;
    }

    /**
     * Gets the value of the insuranceBook property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsuranceBook() {
        return insuranceBook;
    }

    /**
     * Sets the value of the insuranceBook property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsuranceBook(String value) {
        this.insuranceBook = value;
    }

    /**
     * Gets the value of the travelTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelTime() {
        return travelTime;
    }

    /**
     * Sets the value of the travelTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelTime(String value) {
        this.travelTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the lastDateUtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastDateUtc() {
        return lastDateUtc;
    }

    /**
     * Sets the value of the lastDateUtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastDateUtc(String value) {
        this.lastDateUtc = value;
    }

    /**
     * Gets the value of the lastSheet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSheet() {
        return lastSheet;
    }

    /**
     * Sets the value of the lastSheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSheet(String value) {
        this.lastSheet = value;
    }

    /**
     * Gets the value of the testSetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestSetType() {
        return testSetType;
    }

    /**
     * Sets the value of the testSetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestSetType(String value) {
        this.testSetType = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the reasonEnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonEnum() {
        return reasonEnum;
    }

    /**
     * Sets the value of the reasonEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonEnum(String value) {
        this.reasonEnum = value;
    }

    /**
     * Gets the value of the sheetNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSheetNum() {
        return sheetNum;
    }

    /**
     * Sets the value of the sheetNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSheetNum(String value) {
        this.sheetNum = value;
    }

    /**
     * Gets the value of the crewSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrewSize() {
        return crewSize;
    }

    /**
     * Sets the value of the crewSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrewSize(String value) {
        this.crewSize = value;
    }

    /**
     * Gets the value of the counter1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounter1() {
        return counter1;
    }

    /**
     * Sets the value of the counter1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounter1(String value) {
        this.counter1 = value;
    }

    /**
     * Gets the value of the counter2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounter2() {
        return counter2;
    }

    /**
     * Sets the value of the counter2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounter2(String value) {
        this.counter2 = value;
    }

    /**
     * Gets the value of the counter3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounter3() {
        return counter3;
    }

    /**
     * Sets the value of the counter3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounter3(String value) {
        this.counter3 = value;
    }

    /**
     * Gets the value of the resonatorCounter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResonatorCounter() {
        return resonatorCounter;
    }

    /**
     * Sets the value of the resonatorCounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResonatorCounter(String value) {
        this.resonatorCounter = value;
    }

    /**
     * Gets the value of the resonatorDateTestedUtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResonatorDateTestedUtc() {
        return resonatorDateTestedUtc;
    }

    /**
     * Sets the value of the resonatorDateTestedUtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResonatorDateTestedUtc(String value) {
        this.resonatorDateTestedUtc = value;
    }

    /**
     * Gets the value of the factoryCalibrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryCalibrationDate() {
        return factoryCalibrationDate;
    }

    /**
     * Sets the value of the factoryCalibrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryCalibrationDate(String value) {
        this.factoryCalibrationDate = value;
    }

    /**
     * Gets the value of the factoryRecalibrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryRecalibrationDate() {
        return factoryRecalibrationDate;
    }

    /**
     * Sets the value of the factoryRecalibrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryRecalibrationDate(String value) {
        this.factoryRecalibrationDate = value;
    }

    /**
     * Gets the value of the fieldCalibrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldCalibrationDate() {
        return fieldCalibrationDate;
    }

    /**
     * Sets the value of the fieldCalibrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldCalibrationDate(String value) {
        this.fieldCalibrationDate = value;
    }

    /**
     * Gets the value of the lineFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineFrequency() {
        return lineFrequency;
    }

    /**
     * Sets the value of the lineFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineFrequency(String value) {
        this.lineFrequency = value;
    }

    /**
     * Gets the value of the firmwareVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * Sets the value of the firmwareVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmwareVersion(String value) {
        this.firmwareVersion = value;
    }

    /**
     * Gets the value of the dtaVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtaVersion() {
        return dtaVersion;
    }

    /**
     * Sets the value of the dtaVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtaVersion(String value) {
        this.dtaVersion = value;
    }

}