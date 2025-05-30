package com.leoni.usersmanagementleoni.input;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
public class ExcelReader {

    public static void main(String[] args) {
        // Chemin vers le fichier Excel
        //String excelFilePath = "C:\\Users\\admin\\OneDrive\\Documents\\Maven_first_project_stage\\input xlsx file\\input\\src\\main\\java\\leoni\\file.xlsx";
        String excelFilePath = "C:\\Users\\noura\\Downloads\\Maven_first_project_stage\\input xlsx file\\input\\src\\main\\java\\leoni\\file.xlsx";

        List<EmployeeWorkingData> employeeList = readExcelFile(excelFilePath);
        afficherListeEmployee(employeeList);
        totalWorkedHours(employeeList);
    }
    public static void afficherListeEmployee (List<EmployeeWorkingData> employeeList)
    {
        for (EmployeeWorkingData employe : employeeList)
        {
            System.out.println(employe.toString());
        }
    }
    public static boolean verifierEntete(Row entete)
    {
        Set<String> enteteValide = new HashSet<>();
        enteteValide.add("userName");
        enteteValide.add("userLogin");
        enteteValide.add("workedHours");
        enteteValide.add("day");
        Set<String> enteteDonnee = new HashSet<>();
        for(Cell cellule:entete){
            if (cellule.getCellType()== CellType.STRING) {
                enteteDonnee.add(cellule.getStringCellValue());
                
            }
        }
        return enteteDonnee.containsAll(enteteValide);

    }
    public static void afficherColonnesParNom(Sheet sheet,String columnName)
    {
        List<Integer> indices_colonne = new ArrayList<>();
        Row entete =sheet.getRow(0);
        for (Cell cellule : entete)
        {
            if(cellule.getCellType()==CellType.STRING && cellule.getStringCellValue().equals(columnName))
            {
                indices_colonne.add(cellule.getColumnIndex());
            }
        }
        for(int indice_colonne :indices_colonne)
        {
            for(int i=0;i<=sheet.getLastRowNum();i++)
            {
                Row ligne =sheet.getRow(i);
                if (ligne!=null)
                {
                    Cell cellule = ligne.getCell(indice_colonne);
                    if (cellule != null) {
                        if (cellule.getCellType()==CellType.STRING)
                        {
                            System.out.print(cellule.getStringCellValue() + "\n");
                        }
                        else if (cellule.getCellType()==CellType.NUMERIC)
                        {
                            if (DateUtil.isCellDateFormatted(cellule)) {
                                System.out.print(cellule.getDateCellValue() + "\n");
                            } else {
                                System.out.print(cellule.getNumericCellValue() + "\n");
                            }
                        }
                    }   
                }
            }

        }
    }
    public static boolean verifieWorkedHours(Sheet sheet,String columnName)
    {
        Row entete=sheet.getRow(0);
        int indice_colonne=-1;
        for(Cell cellule:entete){
            if (cellule.getCellType()== CellType.STRING && cellule.getStringCellValue().equals(columnName)) {
                indice_colonne=cellule.getColumnIndex();
                break;
            }
        }
        if (indice_colonne==-1) return false;
        
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cellule = row.getCell(indice_colonne);
                if (cellule == null) {
                    return false;
                } else {
                    if (cellule.getCellType() == CellType.STRING) {
                        try {
                            Double.parseDouble(cellule.getStringCellValue());
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else if (cellule.getCellType() != CellType.NUMERIC) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void totalWorkedHours(List<EmployeeWorkingData> employeeList) {
       
        Map<String, Double> totalWorkedHours = new HashMap<>();
        Map<String, Set<String>> daysWorked = new HashMap<>();
        Map<String, Map<String, Double>> dayWorkedHours = new HashMap<>();

        for (EmployeeWorkingData employee : employeeList) {
            String userLogin = employee.getUserLogin();
            double workedHours = employee.getWorkedHours();
            String day = new SimpleDateFormat("dd/MM/yyyy").format(employee.getDay());
    
            //totalWorkedHours.put(userLogin, totalWorkedHours.getOrDefault(userLogin, 0.0) + workedHours);
            if (totalWorkedHours.containsKey(userLogin)) {
                totalWorkedHours.put(userLogin, totalWorkedHours.get(userLogin) + workedHours);
            } else {
                totalWorkedHours.put(userLogin, workedHours);
            }

            if (!daysWorked.containsKey(userLogin)) {
                daysWorked.put(userLogin, new HashSet<String>());
            }
            daysWorked.get(userLogin).add(day);
    
            if (!dayWorkedHours.containsKey(userLogin)) {
                dayWorkedHours.put(userLogin, new HashMap<String, Double>());
            }
            Map<String, Double> userDayWorkedHours = dayWorkedHours.get(userLogin);
            if (userDayWorkedHours.containsKey(day)) {
                userDayWorkedHours.put(day, userDayWorkedHours.get(day) + workedHours);
            } else {
                userDayWorkedHours.put(day, workedHours);
            }
            //double hours = dayWorkedHours.get(userLogin).getOrDefault(day, 0.0);
            //dayWorkedHours.get(userLogin).put(day, hours + workedHours);
        }
    
        for (Map.Entry<String, Double> entry : totalWorkedHours.entrySet()) {
            Set<String> daysWorkedSet = daysWorked.get(entry.getKey());
            System.out.println("User Login: " + entry.getKey() + ", Total Worked Hours: " + entry.getValue());
            System.out.println("Days Worked: " + daysWorkedSet.size());
            System.out.println("Average Hours per Day: " + entry.getValue() / daysWorkedSet.size());
        }
    }
   
    // public static void totalWorkedHours(Sheet sheet) {
    //     Row entete = sheet.getRow(0);
    //     int userLoginIndex = -1;
    //     int workedHoursIndex = -1;
    //     int dayIndex = -1;
    //     if (entete == null) {
    //         System.out.println("Header row is null");
    //         return;
    //     }
    //     for (Cell cellule : entete) {
    //         String cellValue = cellule.getStringCellValue().trim(); // on utilise trim pour les espaces
    //         if ("userLogin".equals(cellValue)) {
    //             userLoginIndex = cellule.getColumnIndex();
    //         } else if ("workedHours".equals(cellValue)) {
    //             workedHoursIndex = cellule.getColumnIndex();
    //         } else if ("day".equals(cellValue)) {
    //             dayIndex = cellule.getColumnIndex();
    //         }
    //     }

    //     Map<String, Double> totalWorkedHours = new HashMap<>();
    //     Map<String, Set<String>> daysWorked = new HashMap<>();
    //     Map<String, Map<String, Double>> dayWorkedHours = new HashMap<>();
    
    //     for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
    //         Row row = sheet.getRow(rowIndex);
    //         if (row != null) {
    //             Cell userLoginCell = row.getCell(userLoginIndex);
    //             Cell workedHoursCell = row.getCell(workedHoursIndex);
    //             Cell dayCell = row.getCell(dayIndex);
    
    //             if (userLoginCell != null && workedHoursCell != null && dayCell != null) {
    //                 String userLogin = userLoginCell.getStringCellValue();
    //                 double workedHours = 0.0;
    //                 String day = "";
    
    //                 if (workedHoursCell.getCellType() == CellType.NUMERIC) {
    //                     workedHours = workedHoursCell.getNumericCellValue();
    //                 } else if (workedHoursCell.getCellType() == CellType.STRING) {
    //                     try {
    //                         workedHours = Double.parseDouble(workedHoursCell.getStringCellValue().trim());
    //                     } catch (NumberFormatException e) {
    //                         System.out.println("il y a une erreur dans le parsing" + (rowIndex + 1));
    //                     }
    //                 }
    //                 if (dayCell.getCellType()==CellType.STRING)
    //                 {
    //                     day=dayCell.getStringCellValue().trim();
    //                 } else if (dayCell.getCellType() == CellType.NUMERIC) {
    //                 if (DateUtil.isCellDateFormatted(dayCell)) {
    //                     day = new SimpleDateFormat("dd/MM/yyyy").format(dayCell.getDateCellValue());
    //                  }
    //                 }
    
    //                 totalWorkedHours.put(userLogin, totalWorkedHours.getOrDefault(userLogin, 0.0) + workedHours);
    //                 if(daysWorked.containsKey(userLogin)==false)
    //                 {
    //                     daysWorked.put(userLogin,new HashSet<String>());
    //                 }
    //                 daysWorked.get(userLogin).add(day);
    //                 if(dayWorkedHours.containsKey(userLogin)==false)
    //                 {
    //                     dayWorkedHours.put(userLogin,new HashMap<String, Double>());
    //                 }
    //                 double hours = dayWorkedHours.get(userLogin).getOrDefault(day, 0.0);
    //                 dayWorkedHours.get(userLogin).put(day, hours+workedHours);
    //             }
    //         }
    //     }
    
    //     for (Map.Entry<String, Double> entry : totalWorkedHours.entrySet()) {
    //         Set<String> daysWorkedaffiche = daysWorked.get(entry.getKey());
    //         System.out.println("User Login:"+entry.getKey()+",Total Worked Hours:"+entry.getValue());
    //         System.out.println("Days Worked:" + daysWorkedaffiche.size());
    //         System.out.println("Average Hours per Day: " + entry.getValue()/daysWorkedaffiche.size());
    //     }
    // }
    
    public static Map<String,Integer> getIndicesColonne(Row entete)
    {
        Map<String,Integer> indices_colonne =new HashMap<>();
        for (Cell cellule : entete)
        {
            if (cellule.getCellType() == CellType.STRING) {
            {
                if (cellule.getStringCellValue().equals("userName") || cellule.getStringCellValue().equals("userLogin") || cellule.getStringCellValue().equals("workedHours") ||cellule.getStringCellValue().equals("day"))
                {
                    indices_colonne.put(cellule.getStringCellValue(), cellule.getColumnIndex());
                }
            }
            }
        }
        return indices_colonne;
    }
    public static List<EmployeeWorkingData> readExcelFile(String excelFilePath) {
        List<EmployeeWorkingData> employeeList = new ArrayList<>();
    
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
    
            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Integer> indice_de_colonne = getIndicesColonne(sheet.getRow(0));
    
            if (verifierEntete(sheet.getRow(0))) {
                System.out.println("En-tête contient tous les éléments");
            } else {
                System.out.println("En-tête ne contient pas tous les éléments");
                return employeeList;
            }
    
            if (verifieWorkedHours(sheet, "workedHours")) {
                System.out.println("Toutes les valeurs de la colonne workedHours sont des floats");
            } else {
                System.out.println("Il y a des valeurs non floats dans la colonne workedHours");
                return employeeList;
            }
    
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
    
                String userName = row.getCell(indice_de_colonne.get("userName")).getStringCellValue();
                String userLogin = row.getCell(indice_de_colonne.get("userLogin")).getStringCellValue();
    
                Cell workedHoursCell = row.getCell(indice_de_colonne.get("workedHours"));
                float workedHours = 0.0f;
    
                if (workedHoursCell.getCellType() == CellType.NUMERIC) {
                    workedHours = (float) workedHoursCell.getNumericCellValue();
                } else if (workedHoursCell.getCellType() == CellType.STRING) {
                    try {
                        workedHours = Float.parseFloat(workedHoursCell.getStringCellValue().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur de parsing dans la cellule workedHours à la ligne " + (rowIndex + 1));
                    }
                }
    
                Date day = null;
                Cell dayCell = row.getCell(indice_de_colonne.get("day"));
                if (dayCell.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(dayCell)) {
                        day = dayCell.getDateCellValue();
                    }
                } else if (dayCell.getCellType() == CellType.STRING) {
                    try {
                        day = new SimpleDateFormat("dd/MM/yyyy").parse(dayCell.getStringCellValue().trim());
                    } catch (Exception e) {
                        System.out.println("Erreur de parsing dans la cellule day à la ligne " + (rowIndex + 1));
                    }
                }
    
                EmployeeWorkingData employee = new EmployeeWorkingData(userName, userLogin, workedHours, day);
                employeeList.add(employee);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return employeeList;
    }
    
    /*public static List<EmployeeWorkingData> readExcelFile(String excelFilePath) {
        List<EmployeeWorkingData> employeeList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Map<String,Integer> indice_de_colonne = getIndicesColonne(sheet.getRow(0));

            if(verifierEntete(sheet.getRow(0))) {
                System.out.println("En-tête contient tous les éléments");
            } else {
                System.out.println("En-tête ne contient pas tous les éléments");
                return employeeList;
            }

            if (verifieWorkedHours(sheet, "workedHours")) {
                System.out.println("Toutes les valeurs de la colonne workedHours sont des floats");
            } else {
                System.out.println("Il y a des valeurs non floats dans la colonne workedHours");
                return employeeList;
            }

            
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                String userName = row.getCell(indice_de_colonne.get("userName")).getStringCellValue();
                String userLogin = row.getCell(indice_de_colonne.get("userLogin")).getStringCellValue();
                float workedHours = (float) row.getCell(indice_de_colonne.get("workedHours")).getNumericCellValue();
                Date day = row.getCell(indice_de_colonne.get("day")).getDateCellValue();

                EmployeeWorkingData employee = new EmployeeWorkingData(userName, userLogin, workedHours, day);
                employeeList.add(employee);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
    */
    // public static void readExcelFile(String excelFilePath) {
    //     try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
    //          Workbook workbook = new XSSFWorkbook(fis)) {

            
    //         Sheet sheet = workbook.getSheetAt(0);

    //         if(verifierEntete(sheet.getRow(0)))
    //         {
    //             System.out.println("En-tête contient tout les elements");
    //         } else
    //         {
    //             System.out.println("En-tête ne contient pas tout les elements");
    //             return;
    //         }


    //         if (verifieWorkedHours(sheet, "workedHours")) {
    //             System.out.println("Toutes les valeurs de la colonne workedHours sont des floats");
    //         } else {
    //             System.out.println("Il y a des valeurs non floats dans la colonne workedHours");
    //             return;
    //         }


    //         //afficherColonnesParNom(sheet, "day");

    //         //totalWorkedHours(sheet);

    //         // Obtenir l'itérateur de lignes
    //         for (Row row : sheet) {
    //             for (Cell cell : row) {
    //                 // Afficher la valeur de la cellule
    //                 switch (cell.getCellType()) {
    //                     case STRING:
    //                         System.out.print(cell.getStringCellValue() + "\t");
    //                         break;
    //                     case NUMERIC:
    //                         if (DateUtil.isCellDateFormatted(cell)) {
    //                             System.out.print(cell.getDateCellValue() + "\t");
    //                         } else {
    //                             System.out.print(cell.getNumericCellValue() + "\t");
    //                         }
    //                         break;
    //                     default:
    //                         System.out.print("Unknown Value\t");
    //                         break;
    //                 }
    //             }
    //             System.out.println();
    //         }

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
