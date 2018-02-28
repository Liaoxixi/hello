package liaoxixi.importmysql;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import liaoxixi.App;
import liaoxixi.entity.PArea;
import liaoxixi.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class importPAreaByJson {

    private JsonMapper jm = new JsonMapper(JsonInclude.Include.ALWAYS);

    private static final Logger LOG = LoggerFactory.getLogger(importPAreaByJson.class);

    public static void main(String[] args) throws Exception {

        importPAreaByJson uzd = new importPAreaByJson();

        Properties prop = new Properties();

//        InputStream in = new BufferedInputStream(new FileInputStream("../ga-mass-web-cent-4.0.0/bin/work/webapp/WEB-INF/classes/db/db.properties"));
//		  InputStream in = new BufferedInputStream(new FileInputStream("E:/fenxianju2/ga-mass-web-cent/src/main/resources/db/db.properties"));
        InputStream in = uzd.getClass().getResourceAsStream("/db/db.properties");

		prop.load(in);

		String driver = prop.getProperty("jdbc.driver");
		String url = prop.getProperty("jdbc.url");
		String userName = prop.getProperty("jdbc.username");
		String passWord = prop.getProperty("jdbc.password");

//        String filePath = "E:\\263EM\\pArea_json2.json";
        String filePath = prop.getProperty("filePath");

        List<PArea> pAreaList = uzd.readJsonFile(filePath);

//        String url = "jdbc:mysql://" + args[0] + ":3306/"+args[1]+"?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
//        String url = "jdbc:mysql://192.168.0.166:3306/gacenter_v4?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
//        String driver = "com.mysql.jdbc.Driver";
//        String userName = "root";
//        String passWord = "surfilter1218";

        url = url + "&user=" + userName + "&password=" + passWord;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);

            LOG.info("成功连接mysql数据库!");

            Long startTime = System.currentTimeMillis();

            uzd.importData(pAreaList, conn);

            LOG.info("更新完成，总耗时" + (System.currentTimeMillis() - startTime) + "ms");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

    }

    public List<PArea> readJsonFile(String filePath) throws Exception {
        BufferedReader bf = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        bf.close();
        //String json =  FileUtils.readFileToString(new File(filePath), "UTF-8");
        String json = sb.toString();

        JavaType javaType = jm.getMapper().getTypeFactory().constructParametricType(ArrayList.class, PArea.class);

        return jm.getMapper().readValue(json, javaType);
    }

    public void importData(List<PArea> pAreaList, Connection conn) throws SQLException {
        Statement stmt1 = conn.createStatement();
        String headSql = "INSERT INTO pArea_temp VALUES";
        StringBuilder sb = new StringBuilder(headSql);
        for (int i = 0; i < pAreaList.size(); i ++) {
            PArea pArea = pAreaList.get(i);
            sb.append(",(")
                .append(pArea.getId()).append(",")
                .append(pArea.getArea()).append(",")
                .append(pArea.getConstant_type()).append(",")
                .append("\"").append(pArea.getDept_code() == null ? "" : pArea.getDept_code()).append("\"").append(",")
                .append("\"").append(pArea.getMultipolygon() == null ? "" : pArea.getMultipolygon()).append("\"").append(",")
                .append("\"").append(pArea.getName() == null ? "" : pArea.getName()).append("\"").append(",")
                .append("\"").append(pArea.getParent_code() == null ? "" : pArea.getParent_code()).append("\"").append(",")
                .append("\"") .append(pArea.getQb_type() == null ? "" : pArea.getQb_type()).append("\"").append(",")
                .append("\"").append(pArea.getStatus() == null ? "" : pArea.getStatus()).append("\"").append(",")
                .append(pArea.getXmax()).append(",")
                .append(pArea.getXmin()).append(",")
                .append(pArea.getYmax()).append(",")
                .append(pArea.getYmin()).append(",")
                .append("\"").append(pArea.getShape() == null ? "" : pArea.getShape()).append("\"")
                .append(")");
            if ((i + 1) % 100 == 0 || i == pAreaList.size() - 1) {
                String sql = sb.toString().replaceFirst(",", "") + ";";
                stmt1.execute(sql);
                sb.delete(0, sb.length());
                sb = new StringBuilder(headSql);
            }
        }
    }

}
