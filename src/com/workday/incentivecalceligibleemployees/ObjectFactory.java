
package com.workday.incentivecalceligibleemployees;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.workday.incentivecalceligibleemployees package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExecuteReport_QNAME = new QName("urn:com.workday.report/Incentive__Input_Template_Report_-_BNL", "Execute_Report");
    private final static QName _ReportData_QNAME = new QName("urn:com.workday.report/Incentive__Input_Template_Report_-_BNL", "Report_Data");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.workday.incentivecalceligibleemployees
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReportDataType }
     * 
     */
    public ReportDataType createReportDataType() {
        return new ReportDataType();
    }

    /**
     * Create an instance of {@link ExecuteReportType }
     * 
     */
    public ExecuteReportType createExecuteReportType() {
        return new ExecuteReportType();
    }

    /**
     * Create an instance of {@link LocationObjectIDType }
     * 
     */
    public LocationObjectIDType createLocationObjectIDType() {
        return new LocationObjectIDType();
    }

    /**
     * Create an instance of {@link ReportParametersType }
     * 
     */
    public ReportParametersType createReportParametersType() {
        return new ReportParametersType();
    }

    /**
     * Create an instance of {@link JobProfileObjectIDType }
     * 
     */
    public JobProfileObjectIDType createJobProfileObjectIDType() {
        return new JobProfileObjectIDType();
    }

    /**
     * Create an instance of {@link OrganizationObjectType }
     * 
     */
    public OrganizationObjectType createOrganizationObjectType() {
        return new OrganizationObjectType();
    }

    /**
     * Create an instance of {@link MoneyType }
     * 
     */
    public MoneyType createMoneyType() {
        return new MoneyType();
    }

    /**
     * Create an instance of {@link UniqueIdentifierObjectType }
     * 
     */
    public UniqueIdentifierObjectType createUniqueIdentifierObjectType() {
        return new UniqueIdentifierObjectType();
    }

    /**
     * Create an instance of {@link UniqueIdentifierObjectIDType }
     * 
     */
    public UniqueIdentifierObjectIDType createUniqueIdentifierObjectIDType() {
        return new UniqueIdentifierObjectIDType();
    }

    /**
     * Create an instance of {@link LocationObjectType }
     * 
     */
    public LocationObjectType createLocationObjectType() {
        return new LocationObjectType();
    }

    /**
     * Create an instance of {@link JobProfileObjectType }
     * 
     */
    public JobProfileObjectType createJobProfileObjectType() {
        return new JobProfileObjectType();
    }

    /**
     * Create an instance of {@link ReportEntryType }
     * 
     */
    public ReportEntryType createReportEntryType() {
        return new ReportEntryType();
    }

    /**
     * Create an instance of {@link OrganizationObjectIDType }
     * 
     */
    public OrganizationObjectIDType createOrganizationObjectIDType() {
        return new OrganizationObjectIDType();
    }

    /**
     * Create an instance of {@link AuthenticationType }
     * 
     */
    public AuthenticationType createAuthenticationType() {
        return new AuthenticationType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteReportType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:com.workday.report/Incentive__Input_Template_Report_-_BNL", name = "Execute_Report")
    public JAXBElement<ExecuteReportType> createExecuteReport(ExecuteReportType value) {
        return new JAXBElement<ExecuteReportType>(_ExecuteReport_QNAME, ExecuteReportType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:com.workday.report/Incentive__Input_Template_Report_-_BNL", name = "Report_Data")
    public JAXBElement<ReportDataType> createReportData(ReportDataType value) {
        return new JAXBElement<ReportDataType>(_ReportData_QNAME, ReportDataType.class, null, value);
    }

}
