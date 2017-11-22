package com.markklim.taxi.drive.app.component.file_converters

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook


/**
 * Created by viktor on 20.11.17.
 */
public class ExcelToPojoConverter extends FileToPojoConverter {

    @Override
    public Workbook createWorkbook(InputStream fis) {
        return new XSSFWorkbook(fis)
    }
}