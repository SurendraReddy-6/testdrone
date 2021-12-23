//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.12.15 at 01:24:59 PM IST 
//


package com.spxts.tdms.dobleUtils;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="external-system-properties" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApparatusType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Apparatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="autotransformer-nameplate" type="{}transformer-template-type"/>
 *           &lt;element name="two-winding-transformer-nameplate" type="{}transformer-template-type"/>
 *           &lt;element name="three-winding-transformer-nameplate" type="{}transformer-template-type"/>
 *         &lt;/choice>
 *         &lt;element name="dta-sessions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dta-session" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice>
 *                             &lt;element name="autotransformer-with-tertiary" type="{}transformer-type"/>
 *                             &lt;element name="two-winding-transformer" type="{}transformer-type"/>
 *                             &lt;element name="three-winding-transformer" type="{}transformer-type"/>
 *                             &lt;element name="autotransformer-without-tertiary" type="{}transformer-type"/>
 *                           &lt;/choice>
 *                           &lt;attribute name="current-timezone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="utc-offset-minutes" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="division" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="cct-designation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="session-note" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="session-created-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="first-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="last-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="copyright-notice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="apparatus-note" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="created-by-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modified-by-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xml-version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="test-row-notes" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "externalSystemProperties",
    "apparatusType",
    "apparatus",
    "autotransformerNameplate",
    "twoWindingTransformerNameplate",
    "threeWindingTransformerNameplate",
    "dtaSessions",
    "copyrightNotice"
})
@XmlRootElement(name = "DataModel-R2")
public class DataModelR2 {

    @XmlElement(name = "external-system-properties", required = true)
    protected String externalSystemProperties;
    @XmlElement(name = "ApparatusType", required = true)
    protected String apparatusType;
    @XmlElement(name = "Apparatus", required = true)
    protected String apparatus;
    @XmlElement(name = "autotransformer-nameplate")
    protected TransformerTemplateType autotransformerNameplate;
    @XmlElement(name = "two-winding-transformer-nameplate")
    protected TransformerTemplateType twoWindingTransformerNameplate;
    @XmlElement(name = "three-winding-transformer-nameplate")
    protected TransformerTemplateType threeWindingTransformerNameplate;
    @XmlElement(name = "dta-sessions", required = true)
    protected DataModelR2 .DtaSessions dtaSessions;
    @XmlElement(name = "copyright-notice", required = true)
    protected String copyrightNotice;
    @XmlAttribute(name = "apparatus-note")
    protected String apparatusNote;
    @XmlAttribute(name = "created-by-version")
    protected String createdByVersion;
    @XmlAttribute(name = "modified-by-version")
    protected String modifiedByVersion;
    @XmlAttribute(name = "xml-version")
    protected String xmlVersion;
    @XmlAttribute(name = "test-row-notes")
    protected String testRowNotes;

    /**
     * Gets the value of the externalSystemProperties property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSystemProperties() {
        return externalSystemProperties;
    }

    /**
     * Sets the value of the externalSystemProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSystemProperties(String value) {
        this.externalSystemProperties = value;
    }

    /**
     * Gets the value of the apparatusType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApparatusType() {
        return apparatusType;
    }

    /**
     * Sets the value of the apparatusType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApparatusType(String value) {
        this.apparatusType = value;
    }

    /**
     * Gets the value of the apparatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApparatus() {
        return apparatus;
    }

    /**
     * Sets the value of the apparatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApparatus(String value) {
        this.apparatus = value;
    }

    /**
     * Gets the value of the autotransformerNameplate property.
     * 
     * @return
     *     possible object is
     *     {@link TransformerTemplateType }
     *     
     */
    public TransformerTemplateType getAutotransformerNameplate() {
        return autotransformerNameplate;
    }

    /**
     * Sets the value of the autotransformerNameplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformerTemplateType }
     *     
     */
    public void setAutotransformerNameplate(TransformerTemplateType value) {
        this.autotransformerNameplate = value;
    }

    /**
     * Gets the value of the twoWindingTransformerNameplate property.
     * 
     * @return
     *     possible object is
     *     {@link TransformerTemplateType }
     *     
     */
    public TransformerTemplateType getTwoWindingTransformerNameplate() {
        return twoWindingTransformerNameplate;
    }

    /**
     * Sets the value of the twoWindingTransformerNameplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformerTemplateType }
     *     
     */
    public void setTwoWindingTransformerNameplate(TransformerTemplateType value) {
        this.twoWindingTransformerNameplate = value;
    }

    /**
     * Gets the value of the threeWindingTransformerNameplate property.
     * 
     * @return
     *     possible object is
     *     {@link TransformerTemplateType }
     *     
     */
    public TransformerTemplateType getThreeWindingTransformerNameplate() {
        return threeWindingTransformerNameplate;
    }

    /**
     * Sets the value of the threeWindingTransformerNameplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformerTemplateType }
     *     
     */
    public void setThreeWindingTransformerNameplate(TransformerTemplateType value) {
        this.threeWindingTransformerNameplate = value;
    }

    /**
     * Gets the value of the dtaSessions property.
     * 
     * @return
     *     possible object is
     *     {@link DataModelR2 .DtaSessions }
     *     
     */
    public DataModelR2 .DtaSessions getDtaSessions() {
        return dtaSessions;
    }

    /**
     * Sets the value of the dtaSessions property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataModelR2 .DtaSessions }
     *     
     */
    public void setDtaSessions(DataModelR2 .DtaSessions value) {
        this.dtaSessions = value;
    }

    /**
     * Gets the value of the copyrightNotice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyrightNotice() {
        return copyrightNotice;
    }

    /**
     * Sets the value of the copyrightNotice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyrightNotice(String value) {
        this.copyrightNotice = value;
    }

    /**
     * Gets the value of the apparatusNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApparatusNote() {
        return apparatusNote;
    }

    /**
     * Sets the value of the apparatusNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApparatusNote(String value) {
        this.apparatusNote = value;
    }

    /**
     * Gets the value of the createdByVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedByVersion() {
        return createdByVersion;
    }

    /**
     * Sets the value of the createdByVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedByVersion(String value) {
        this.createdByVersion = value;
    }

    /**
     * Gets the value of the modifiedByVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifiedByVersion() {
        return modifiedByVersion;
    }

    /**
     * Sets the value of the modifiedByVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifiedByVersion(String value) {
        this.modifiedByVersion = value;
    }

    /**
     * Gets the value of the xmlVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlVersion() {
        return xmlVersion;
    }

    /**
     * Sets the value of the xmlVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlVersion(String value) {
        this.xmlVersion = value;
    }

    /**
     * Gets the value of the testRowNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestRowNotes() {
        return testRowNotes;
    }

    /**
     * Sets the value of the testRowNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestRowNotes(String value) {
        this.testRowNotes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="dta-session" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice>
     *                   &lt;element name="autotransformer-with-tertiary" type="{}transformer-type"/>
     *                   &lt;element name="two-winding-transformer" type="{}transformer-type"/>
     *                   &lt;element name="three-winding-transformer" type="{}transformer-type"/>
     *                   &lt;element name="autotransformer-without-tertiary" type="{}transformer-type"/>
     *                 &lt;/choice>
     *                 &lt;attribute name="current-timezone" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="utc-offset-minutes" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="division" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="cct-designation" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="session-note" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="session-created-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="first-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="last-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dtaSession"
    })
    public static class DtaSessions {

        @XmlElement(name = "dta-session", required = true)
        protected List<DataModelR2 .DtaSessions.DtaSession> dtaSession;

        /**
         * Gets the value of the dtaSession property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dtaSession property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDtaSession().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataModelR2 .DtaSessions.DtaSession }
         * 
         * 
         */
        public List<DataModelR2 .DtaSessions.DtaSession> getDtaSession() {
            if (dtaSession == null) {
                dtaSession = new ArrayList<DataModelR2 .DtaSessions.DtaSession>();
            }
            return this.dtaSession;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;choice>
         *         &lt;element name="autotransformer-with-tertiary" type="{}transformer-type"/>
         *         &lt;element name="two-winding-transformer" type="{}transformer-type"/>
         *         &lt;element name="three-winding-transformer" type="{}transformer-type"/>
         *         &lt;element name="autotransformer-without-tertiary" type="{}transformer-type"/>
         *       &lt;/choice>
         *       &lt;attribute name="current-timezone" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="utc-offset-minutes" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="division" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="cct-designation" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="session-note" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="session-created-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="first-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="last-test-date-utc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "autotransformerWithTertiary",
            "twoWindingTransformer",
            "threeWindingTransformer",
            "autotransformerWithoutTertiary"
        })
        public static class DtaSession {

            @XmlElement(name = "autotransformer-with-tertiary")
            protected TransformerType autotransformerWithTertiary;
            @XmlElement(name = "two-winding-transformer")
            protected TransformerType twoWindingTransformer;
            @XmlElement(name = "three-winding-transformer")
            protected TransformerType threeWindingTransformer;
            @XmlElement(name = "autotransformer-without-tertiary")
            protected TransformerType autotransformerWithoutTertiary;
            @XmlAttribute(name = "current-timezone")
            protected String currentTimezone;
            @XmlAttribute(name = "utc-offset-minutes")
            protected String utcOffsetMinutes;
            @XmlAttribute(name = "location")
            protected String location;
            @XmlAttribute(name = "division")
            protected String division;
            @XmlAttribute(name = "company")
            protected String company;
            @XmlAttribute(name = "cct-designation")
            protected String cctDesignation;
            @XmlAttribute(name = "session-note")
            protected String sessionNote;
            @XmlAttribute(name = "session-created-date-utc")
            protected String sessionCreatedDateUtc;
            @XmlAttribute(name = "first-test-date-utc")
            protected String firstTestDateUtc;
            @XmlAttribute(name = "last-test-date-utc")
            protected String lastTestDateUtc;

            /**
             * Gets the value of the autotransformerWithTertiary property.
             * 
             * @return
             *     possible object is
             *     {@link TransformerType }
             *     
             */
            public TransformerType getAutotransformerWithTertiary() {
                return autotransformerWithTertiary;
            }

            /**
             * Sets the value of the autotransformerWithTertiary property.
             * 
             * @param value
             *     allowed object is
             *     {@link TransformerType }
             *     
             */
            public void setAutotransformerWithTertiary(TransformerType value) {
                this.autotransformerWithTertiary = value;
            }

            /**
             * Gets the value of the twoWindingTransformer property.
             * 
             * @return
             *     possible object is
             *     {@link TransformerType }
             *     
             */
            public TransformerType getTwoWindingTransformer() {
                return twoWindingTransformer;
            }

            /**
             * Sets the value of the twoWindingTransformer property.
             * 
             * @param value
             *     allowed object is
             *     {@link TransformerType }
             *     
             */
            public void setTwoWindingTransformer(TransformerType value) {
                this.twoWindingTransformer = value;
            }

            /**
             * Gets the value of the threeWindingTransformer property.
             * 
             * @return
             *     possible object is
             *     {@link TransformerType }
             *     
             */
            public TransformerType getThreeWindingTransformer() {
                return threeWindingTransformer;
            }

            /**
             * Sets the value of the threeWindingTransformer property.
             * 
             * @param value
             *     allowed object is
             *     {@link TransformerType }
             *     
             */
            public void setThreeWindingTransformer(TransformerType value) {
                this.threeWindingTransformer = value;
            }

            /**
             * Gets the value of the autotransformerWithoutTertiary property.
             * 
             * @return
             *     possible object is
             *     {@link TransformerType }
             *     
             */
            public TransformerType getAutotransformerWithoutTertiary() {
                return autotransformerWithoutTertiary;
            }

            /**
             * Sets the value of the autotransformerWithoutTertiary property.
             * 
             * @param value
             *     allowed object is
             *     {@link TransformerType }
             *     
             */
            public void setAutotransformerWithoutTertiary(TransformerType value) {
                this.autotransformerWithoutTertiary = value;
            }

            /**
             * Gets the value of the currentTimezone property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrentTimezone() {
                return currentTimezone;
            }

            /**
             * Sets the value of the currentTimezone property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrentTimezone(String value) {
                this.currentTimezone = value;
            }

            /**
             * Gets the value of the utcOffsetMinutes property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUtcOffsetMinutes() {
                return utcOffsetMinutes;
            }

            /**
             * Sets the value of the utcOffsetMinutes property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUtcOffsetMinutes(String value) {
                this.utcOffsetMinutes = value;
            }

            /**
             * Gets the value of the location property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLocation() {
                return location;
            }

            /**
             * Sets the value of the location property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLocation(String value) {
                this.location = value;
            }

            /**
             * Gets the value of the division property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDivision() {
                return division;
            }

            /**
             * Sets the value of the division property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDivision(String value) {
                this.division = value;
            }

            /**
             * Gets the value of the company property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCompany() {
                return company;
            }

            /**
             * Sets the value of the company property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCompany(String value) {
                this.company = value;
            }

            /**
             * Gets the value of the cctDesignation property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCctDesignation() {
                return cctDesignation;
            }

            /**
             * Sets the value of the cctDesignation property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCctDesignation(String value) {
                this.cctDesignation = value;
            }

            /**
             * Gets the value of the sessionNote property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSessionNote() {
                return sessionNote;
            }

            /**
             * Sets the value of the sessionNote property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSessionNote(String value) {
                this.sessionNote = value;
            }

            /**
             * Gets the value of the sessionCreatedDateUtc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSessionCreatedDateUtc() {
                return sessionCreatedDateUtc;
            }

            /**
             * Sets the value of the sessionCreatedDateUtc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSessionCreatedDateUtc(String value) {
                this.sessionCreatedDateUtc = value;
            }

            /**
             * Gets the value of the firstTestDateUtc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFirstTestDateUtc() {
                return firstTestDateUtc;
            }

            /**
             * Sets the value of the firstTestDateUtc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFirstTestDateUtc(String value) {
                this.firstTestDateUtc = value;
            }

            /**
             * Gets the value of the lastTestDateUtc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastTestDateUtc() {
                return lastTestDateUtc;
            }

            /**
             * Sets the value of the lastTestDateUtc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastTestDateUtc(String value) {
                this.lastTestDateUtc = value;
            }

        }

    }

}