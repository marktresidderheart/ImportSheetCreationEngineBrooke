
package com.workday.incentivecalceligibleemployees;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Execute_ReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Execute_ReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Report_Parameters" type="{urn:com.workday.report/Incentive__Input_Template_Report_-_BNL}Report_ParametersType" minOccurs="0"/>
 *         &lt;element name="Authentication" type="{urn:com.workday.report/Incentive__Input_Template_Report_-_BNL}AuthenticationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Execute_ReportType", propOrder = {
    "reportParameters",
    "authentication"
})
public class ExecuteReportType {

    @XmlElement(name = "Report_Parameters")
    protected ReportParametersType reportParameters;
    @XmlElement(name = "Authentication")
    protected AuthenticationType authentication;

    /**
     * Gets the value of the reportParameters property.
     * 
     * @return
     *     possible object is
     *     {@link ReportParametersType }
     *     
     */
    public ReportParametersType getReportParameters() {
        return reportParameters;
    }

    /**
     * Sets the value of the reportParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportParametersType }
     *     
     */
    public void setReportParameters(ReportParametersType value) {
        this.reportParameters = value;
    }

    /**
     * Gets the value of the authentication property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationType }
     *     
     */
    public AuthenticationType getAuthentication() {
        return authentication;
    }

    /**
     * Sets the value of the authentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationType }
     *     
     */
    public void setAuthentication(AuthenticationType value) {
        this.authentication = value;
    }

}
