package com.demo.util;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Description: easyexcel 百分比write handler
 *
 * @author zzl
 * date: 2020/6/8
 */
public class EasyExcelPercentWriteHandler implements CellWriteHandler {

    private static final Set<String> FIELD_SET = Stream.of("safeStockRatio", "onlineRatio", "offlineRatio").collect(Collectors.toSet());


    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    /**
     * Called after all operations on the cell have been completed
     *
     * @param writeSheetHolder
     * @param writeTableHolder Nullable.It is null without using table writes.
     * @param cellDataList     Nullable.It is null in the case of add header.There may be several when fill the data.
     * @param cell
     * @param head             Nullable.It is null in the case of fill data and without head.
     * @param relativeRowIndex Nullable.It is null in the case of fill data.
     * @param isHead
     */
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (!isHead && FIELD_SET.contains(head.getFieldName())) {
            CellStyle cellStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            DataFormat dataFormat = writeSheetHolder.getSheet().getWorkbook().createDataFormat();
            cellStyle.setDataFormat(dataFormat.getFormat("0%"));
            cell.setCellType(CellType.NUMERIC);
            cell.setCellStyle(cellStyle);
        }
    }
}
