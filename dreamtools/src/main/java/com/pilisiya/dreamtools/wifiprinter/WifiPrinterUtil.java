package com.pilisiya.dreamtools.wifiprinter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xujiang on  2024/7/26 12:05
 * desc：
 * usefulness:
 */
public class WifiPrinterUtil {
    public static void convertBitmapToPdf(Bitmap bitmap) {
        // 创建PDF文档
        PdfDocument pdfDocument = new PdfDocument();
        // 页面信息，设置页面大小
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        // 开始一个新页面
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        // 获取页面的Canvas
        Canvas canvas = page.getCanvas();
        // 绘制Bitmap到Canvas
        canvas.drawBitmap(bitmap, 0, 0, null);

        // 结束页面，添加到文档
        pdfDocument.finishPage(page);

        // 外部存储的目录
        File file = new File(Environment.getExternalStorageDirectory() + "/img.pdf");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            // 写入文件
            pdfDocument.writeTo(fos);
            // 关闭文档
            pdfDocument.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
