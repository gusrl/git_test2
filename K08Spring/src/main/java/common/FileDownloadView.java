package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {
	//생성자 
	public FileDownloadView(){
        //응답헤더에 모르는 컨텐츠 타입을 실어주면 웹브라우저는 자기가 열수없는 파일이라 생각하고 다운로드 창을 띄운다. 
		setContentType("apllication/download; charset=utf-8");
    }
        
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest req, HttpServletResponse res) throws Exception {
       //모델쪽에서 넘겨받은 다음에 
        File file = (File) model.get("downloadFile");
        String oriFileName = (String) model.get("oriFileName");

        res.setContentType(getContentType());
        res.setContentLength((int) file.length());
        res.setHeader("Content-Disposition", "attachment; filename=\"" + 
                java.net.URLEncoder.encode(oriFileName, "utf-8") + "\";");
        res.setHeader("Content-Transfer-Encoding", "binary");
        
        OutputStream out = res.getOutputStream();
        FileInputStream fis = null;
        
        try {          
        	//파일 스트림이 해준다.
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try { 
                    fis.close(); 
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }        
        out.flush();
    }
}
