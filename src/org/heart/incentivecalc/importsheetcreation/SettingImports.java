package org.heart.incentivecalc.importsheetcreation;

import com.opencsv.CSVReader;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

import com.workday.incentivecalceligibleemployees.*;

public class SettingImports {

    public SettingImports() {

    }

    public static ArrayList<EmployeeIncentiveInput> GetWorkdayEligibleEmployees(String wdUser, String wdPassword, String wdEndpoint) {

        ArrayList<EmployeeIncentiveInput> eligibleEmployees = new ArrayList<>();

        try {

            System.out.println("Connecting to Workday Employee Eligibility Report");

            // Create the Web Service client stub
            ReportService service = new ReportService();
            ReportPort port = service.getReport();

            // Add the WorkdayCredentials handler to the client stub
            WorkdayCredentials.addWorkdayCredentials((BindingProvider) port,
                    wdUser, wdPassword);

            // Assign the Endpoint URL
            Map<String, Object> requestContext = ((BindingProvider) port)
                    .getRequestContext();
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                    wdEndpoint);

            // Set the current date/time
            GregorianCalendar cal = new GregorianCalendar();
            XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(cal);

            // Create a "request" object
            ExecuteReportType request = new ExecuteReportType();

            // Submit the request creating the "response" object
            ReportParametersType parametersType = new ReportParametersType();
            AuthenticationType authType = new AuthenticationType();

            List<ReportEntryType> response = port.executeReport(parametersType, authType);

            System.out.println("Number of eligibility employees to retrieve:"+response.size());

            String employeeID, employeeName,region, jobCode, jobProfile, businessTitle, incentivePlan, department,location;
            Date hireDate, timeInJob;

            for (ReportEntryType reportEntryType : response) {
                employeeID = reportEntryType.getEmployeeID();
                employeeName = reportEntryType.getEmployeeName();
                region = returnRegionAbreviation(reportEntryType.getRegion().getDescriptor());
                jobCode = reportEntryType.getJobCode();
                jobProfile = reportEntryType.getJobProfile().getDescriptor();
                businessTitle = reportEntryType.getBusinessTitle();
                hireDate = new SimpleDateFormat("yyyy-MM-dd-hh:mm").parse(reportEntryType.getHireDate().toString());
                timeInJob = new SimpleDateFormat("yyyy-MM-dd-hh:mm").parse(reportEntryType.getTimeInJobProfileStartDate().toString());
                incentivePlan = reportEntryType.getIncentivePlan();
                department = reportEntryType.getDepartment().getDescriptor().replaceAll("/","-");
                location = reportEntryType.getLocation().getDescriptor();
                String sortCriteria1 = "incentivePlan";
                String sortCriteria2 = "department";
                String sortCriteria3 = "";

                EmployeeIncentiveInput employee = new EmployeeIncentiveInput(employeeID,employeeName,region,jobCode,jobProfile,businessTitle,hireDate,timeInJob,incentivePlan,department,location,sortCriteria1,sortCriteria2,sortCriteria3,"New");

                eligibleEmployees.add(employee);
            }
        } catch (DatatypeConfigurationException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Number of eligible employees:"+eligibleEmployees.size());
        return eligibleEmployees;
    }

    public static ArrayList<EmployeeIncentiveInput> OpenEmployeeImportCSV(String fileLocation){

        long countOfLines=0;
        try {
            countOfLines = Files.lines(Paths.get(new File(fileLocation).getPath()), Charset.forName("ISO_8859_1")).count();
            System.out.println(String.format("There are %s lines in the eligible employees file", countOfLines));
        } catch (IOException e) {
            System.out.println("No File Found");
        }

        CSVReader reader = null;
        ArrayList<EmployeeIncentiveInput> employeeIncentiveInputs = new ArrayList<>();

        try {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(fileLocation),',');
            String [] nextLine;

            String employeeID, employeeName,region, jobCode, jobProfile, businessTitle, incentivePlan, department,location;
            Date hireDate, timeInJob;

            boolean headerRow = true;

            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                if (!headerRow) {
                    employeeID = nextLine[0];
                    employeeName = nextLine[1];
                    region = returnRegionAbreviation(nextLine[2]);
                    jobCode = nextLine[3];
                    jobProfile = nextLine[4];
                    businessTitle = nextLine[5];
                    hireDate = new SimpleDateFormat("MM/dd/yyyy").parse(nextLine[6]);
                    timeInJob = new SimpleDateFormat("MM/dd/yyyy").parse(nextLine[7]);
                    incentivePlan = nextLine[8];
                    department = nextLine[9].replaceAll("/","-");
                    location = nextLine[10];
                    String sortCriteria1 = "incentivePlan";
                    String sortCriteria2 = "department";
                    String sortCriteria3 = "";

                    EmployeeIncentiveInput employee = new EmployeeIncentiveInput(employeeID,employeeName,region,jobCode,jobProfile,businessTitle,hireDate,timeInJob,incentivePlan,department,location,sortCriteria1,sortCriteria2,sortCriteria3,"New");

                    employeeIncentiveInputs.add(employee);
                } else {
                    headerRow = false;
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return employeeIncentiveInputs;
    }

    public static ArrayList<GoalDefinition> ImportGoalSettings(String fileLocation){

        long countOfLines=0;
        try {
            countOfLines = Files.lines(Paths.get(new File(fileLocation).getPath()), Charset.forName("ISO_8859_1")).count();
            System.out.println(String.format("There are %s lines in the goal settings import file", countOfLines));
        } catch (IOException e) {
            System.out.println("No File Found");
        }

        CSVReader reader = null;
        ArrayList<GoalDefinition> goalDefinitions = new ArrayList<>();

        try {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(fileLocation),',');
            String [] nextLine;

            String planName, planCategory, goalName;
            Integer fiscalYear = 0;
            Float max = 0f;
            Float target = 0f;
            Float min = 0f;
            Float goalWeight = 0f;
            Float flatAmount = 0f;

            boolean headerRow = true;

            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                if (!headerRow) {
                    fiscalYear = Integer.parseInt(nextLine[0].substring(0,4));
                    planName = nextLine[1];
                    planCategory = nextLine[2];
                    if (nextLine[3] != null && !nextLine[3].isEmpty()) {
                        max = Float.parseFloat(nextLine[3]);
                    }
                    if (nextLine[4] != null && !nextLine[4].isEmpty()) {
                        target = Float.parseFloat(nextLine[4]);
                    }
                    if (nextLine[5] != null && !nextLine[5].isEmpty()) {
                        min = Float.parseFloat(nextLine[5]);
                    }
                    goalName = nextLine[6];
                    if (nextLine[7] != null && !nextLine[7].isEmpty()) {
                        goalWeight = Float.parseFloat(nextLine[7]);
                    }
                    if (nextLine[8] != null && !nextLine[8].isEmpty()) {
                        flatAmount = Float.parseFloat(nextLine[8]);
                    }

                    GoalDefinition goalDefinition = new GoalDefinition(fiscalYear,planName,planCategory,max,target,min,goalName,goalWeight,flatAmount);

                    goalDefinitions.add(goalDefinition);
                } else {
                    headerRow = false;
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  goalDefinitions;
    }


    public static ArrayList<GoalColumnDefinition> ImportGoalColumnSettings(String fileLocation){

        long countOfLines=0;
        try {
            countOfLines = Files.lines(Paths.get(new File(fileLocation).getPath()), Charset.forName("ISO_8859_1")).count();
            System.out.println(String.format("There are %s lines in the goal column settings import file", countOfLines));
        } catch (IOException e) {
            System.out.println("No File Found");
        }

        CSVReader reader = null;
        ArrayList<GoalColumnDefinition> goalColumnDefinitions = new ArrayList<>();

        try {
            //Get the CSVReader instance with specifying the delimiter to be used
            reader = new CSVReader(new FileReader(fileLocation),',');
            String [] nextLine;

            String columnName, columnType, linkedGoalName, defaultValue, selectionFile,
                    regionException1, regionException2, regionException3, regionException4, regionException5;
            Integer columnOrder = 0;
            Integer columnGoalOrder = 0;
            boolean columnLocked = false;
            boolean includeInInputSheet = false;

            boolean headerRow = true;

            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                if (!headerRow) {
                    columnName = nextLine[0];
                    columnType = nextLine[1];
                    linkedGoalName = nextLine[2];
                    if (nextLine[3] != null && !nextLine[3].isEmpty()) {
                        columnOrder = Integer.parseInt(nextLine[3]);
                    }
                    if (nextLine[4] != null && !nextLine[4].isEmpty()) {
                        columnGoalOrder = Integer.parseInt(nextLine[4]);
                    }
                    if (nextLine[5] != null && !nextLine[5].isEmpty()) {
                        if (nextLine[5].equals("1")) {
                            columnLocked = true;
                        } else {
                            columnLocked = false;
                        }
                    }
                    if (nextLine[6] != null && !nextLine[6].isEmpty()) {
                        if (nextLine[6].equals("1")) {
                            includeInInputSheet = true;
                        } else {
                            includeInInputSheet = false;
                        }
                    }
                    defaultValue = nextLine[7];
                    selectionFile = nextLine[8];
                    regionException1 = nextLine[9];
                    regionException2 = nextLine[10];
                    regionException3 = nextLine[11];
                    regionException4 = nextLine[12];
                    regionException5 = nextLine[13];

                    GoalColumnDefinition goalColumnDefinition = new GoalColumnDefinition(columnName,columnType,linkedGoalName,columnOrder,columnGoalOrder,
                                                                                                            columnLocked,includeInInputSheet,defaultValue,selectionFile,regionException1,
                                                                                                            regionException2,regionException3,regionException4,
                                                                                                            regionException5);

                    goalColumnDefinitions.add(goalColumnDefinition);
                } else {
                    headerRow = false;
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  goalColumnDefinitions;
    }

    private static String returnRegion(String region) {

        String newRegion;

        switch (region) {
            case "FDA" :
                newRegion = "Eastern States";
                break;
            case "MWA" :
                newRegion = "Midwest";
                break;
            case "GSA" :
                newRegion = "Southeast";
                break;
            case "SWA" :
                newRegion = "Southwest";
                break;
            case "WSA" :
                newRegion = "Western States";
                break;
            case "NAT" :
                newRegion = "National Center";
                break;
            default :
                newRegion = region;
        }

        return newRegion;

    }

    private static String returnRegionAbreviation(String region) {

        String newRegion;

        switch (region) {
            case "FDA" :
                newRegion = "ES";
                break;
            case "MWA" :
                newRegion = "MW";
                break;
            case "GSA" :
                newRegion = "SE";
                break;
            case "SWA" :
                newRegion = "SW";
                break;
            case "WSA" :
                newRegion = "WS";
                break;
            case "NAT" :
                newRegion = "NAT";
                break;
            default :
                newRegion = region;
        }

        return newRegion;

    }
}
