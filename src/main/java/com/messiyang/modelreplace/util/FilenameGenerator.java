package com.messiyang.modelreplace.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件名生成器
 *
 * @author messiyang
 */
public class FilenameGenerator {
    /**
     * 每个文件夹最大文件数量
     */
    private static final int MAX_FILE_TOTAL = 100;
    /**
     * 导出文件存放目录
     */
    private static final String EXPORT_DIR = "/Users/messi/Desktop/seal/export/";
    /**
     * 日期格式化
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * 流水码格式化
     */
    private static final DecimalFormat numFormat = new DecimalFormat("000");
    /**
     * 当前文件流水号
     */
    private static int file_index = 1;
    /**
     * 当前目录流水号
     */
    private static int dir_index = 1;
    /**
     * 当前日期
     */
    private static String current_date = "";

    /**
     * 获取Json数据的文件名 <br />
     *
     * 注意：每次传递新日期后，编号都会归位，在执行导出的过程中，如果中途中断，最好从某个日期结束处中断。
     *
     * @param areaCode 地区代号：如330212
     * @param infoType 信息的类型，如：YZXX
     * @param date 信息的创建日期
     * @return 文件的全路径名称
     */
    public static String getJsonFilename(String areaCode, String infoType, Date date) {
        String dateStr = dateFormat.format(date);

        if(!current_date.equals(dateStr)) {
            dir_index = 1;
            file_index = 1;
            current_date = dateStr;
        }
        else {
            if(file_index > MAX_FILE_TOTAL) {
                dir_index ++;
                file_index = 1;
            }
            else {
                file_index ++;
                if(file_index > MAX_FILE_TOTAL) {
                    file_index = 1;
                    dir_index ++;
                }
            }

        }

        String prefix = areaCode  + "_" + infoType + "_" + dateStr;
        String dirname = prefix + "_" + numFormat.format(dir_index);
        String filename = prefix + "_" + numFormat.format(file_index) + ".txt";
        return EXPORT_DIR + dirname + "/" + filename;
    }

    public static String getSealImageFilename(String jsonFileName, String sealCode, String materialType, String imageType,int index){
        String prefix = jsonFileName.substring(0,jsonFileName.lastIndexOf("/")+1);
        return  prefix+sealCode+"_"+materialType+"_"+ index+  "."+imageType;
    }


}
