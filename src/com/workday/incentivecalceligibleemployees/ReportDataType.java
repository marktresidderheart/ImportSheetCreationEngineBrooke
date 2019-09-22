
package com.workday.incentivecalceligibleemployees;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Report_DataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Report_DataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Report_Entry" type="{urn:com.workday.report/Incentive__Input_Template_Report_-_BNL}Report_EntryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Report_DataType", propOrder = {
    "reportEntry"
})
public class ReportDataType {

    @XmlElement(name = "Report_Entry")
    protected List<ReportEntryType> reportEntry;

    /**
     * Gets the value of the reportEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reportEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReportEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReportEntryType }
     * 
     * 
     */
    public List<ReportEntryType> getReportEntry() {
        if (reportEntry == null) {
            reportEntry = new ArrayList<ReportEntryType>();
        }
        return this.reportEntry;
    }

}
