package util;

import java.util.ArrayList;
import java.util.List;

public class testMain {
	
	public static void main(String[] args) {
		List<String> attributeNames = new ArrayList<String>();
		attributeNames.add("Col1");
		attributeNames.add("Col2");
		attributeNames.add("Col3");
		attributeNames.add("Col4");
		attributeNames.add("Col5");
		String tableName="tabel_name";
		int pageNo = 2;
		int pageSize = 20;
		String dataBaseName = "Oracle";
		
		StringBuffer sBuffer = new StringBuffer();
		if ("Mysql".equals(dataBaseName)){
			sBuffer.append("select ");
			for(int i=0;i<attributeNames.size();i++){
				sBuffer.append(attributeNames.get(i));
				if (i!=attributeNames.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" limit ").append((pageNo-1)*pageSize).append(",").append(pageSize);
			
		} else if ("Oracle".equals(dataBaseName)) {
			sBuffer.append("select * from (");
			sBuffer.append("select rownum as rowno, ");
			for(int i=0;i<attributeNames.size();i++){
				sBuffer.append(attributeNames.get(i));
				if (i!=attributeNames.size()-1){
					sBuffer.append(",");
				}
			}
			sBuffer.append(" from ").append(tableName);
			sBuffer.append(" where rownum<= ").append(pageNo*pageSize).append(")");
			sBuffer.append(" where rowno >").append((pageNo-1)*pageSize);
		}
		System.out.println(sBuffer.toString());
		
	}

}
