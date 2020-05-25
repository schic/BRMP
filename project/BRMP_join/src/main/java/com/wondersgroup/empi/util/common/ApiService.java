package com.wondersgroup.empi.util.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Service
public class ApiService implements BeanFactoryAware {
	private BeanFactory beanFactory;

	@Value("${NOT_FOUND_SERVIER}")
	private String NOT_FOUND_SERVIER;
	@Autowired(required = false)
	private RequestConfig requestConfig;

	public String doGet(String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(this.requestConfig);
		CloseableHttpResponse response = null;
		try {
			response = getHttpClient().execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		if (response != null) {
			response.close();
		}
		return null;
	}

	public HttpResult doGet(String url, String username, String password)
    throws ClientProtocolException, IOException
  {
	  
	HttpResult result = null;
    HttpGet httpGet = new HttpGet(url);
    httpGet.setConfig(this.requestConfig);
    String encoding = DatatypeConverter.printBase64Binary((username + ":" + password).getBytes("UTF-8"));
    httpGet.setHeader("Authorization", "Basic " + encoding);
    CloseableHttpResponse response = null;
    try
    {
      response = getHttpClient().execute(httpGet);
      
      int code=response.getStatusLine().getStatusCode();
      
      String entry =EntityUtils.toString(response.getEntity(), "UTF-8");
      
      result = new  HttpResult(code, JSONObject.parseObject(entry));

    }
    finally
    {
      if (response != null) {
        response.close();
      }
    }
    if (response != null) {
      response.close();
    }
    
    return result;
  }

	public String doGet(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		URIBuilder builder = new URIBuilder(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.setParameter((String) entry.getKey(), (String) entry.getValue());
		}
		return doGet(builder.build().toString());
	}
	
	 /**
     * 执行post请求，发送xml数据
     * 
     * @param url
     * @param xml
     * @return
     * @throws IOException
     * @throws DocumentException 
     */
    public HttpResult doPostJson(String url,String json,String username, String password) throws IOException, DocumentException {
        
        //进行数字签名
//        Map<String, String> map=Encryption.buildRequestPara(XMLUtil.xmlBody2map(xml),PRIVATE_KEY);     
//        xml=XMLUtil.map2xmlBody(map, "request");
        
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        String encoding = DatatypeConverter.printBase64Binary((username + ":" + password).getBytes("UTF-8"));
        httpPost.setHeader("Authorization", "Basic " + encoding);
        if (null != json) {
            // 构造一个字符串的实体
            StringEntity stringEntity = new StringEntity(json, ContentType.create("application/json", Consts.UTF_8));
            // 将请求实体设置到httpPost对象中
            httpPost.setEntity(stringEntity);
        }

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = getHttpClient().execute(httpPost);
            
            int code=response.getStatusLine().getStatusCode();
            
            String entry =EntityUtils.toString(response.getEntity(), "UTF-8");
            
            System.out.println(entry);
           
            if(StringUtils.isNotBlank(entry)){
                             
               return new HttpResult(code , JSON.parseObject(entry));
            }  
            
            return new HttpResult(code, entry);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><res><resultCode>202</resultCode><resultDesc>����!</resultDesc><healthExamReportCardDetail><medicalOrgId>PDY00060X511526</medicalOrgId><examinationId>C19347C2EA6C45BEA8BAF54CC9225007</examinationId><patientName>������</patientName><doctorId>8BA8D7B840D449CF8A4F7F28F83264A2</doctorId><doctorName>������</doctorName><symptom>40960</symptom><temperature>36.6</temperature><pulseRate>62</pulseRate><respiratoryRate>16</respiratoryRate><leftDbp>132</leftDbp><leftSbp>82</leftSbp><rightDbp>128</rightDbp><rightSbp>64</rightSbp><height>160</height><weight>55</weight><waistline>68</waistline><BMI>1</BMI><exerciseRate>8 </exerciseRate><dietaryHabit>1</dietaryHabit><smokeState>1</smokeState><drinkRate>1</drinkRate><whetherStop>0</whetherStop><whetherDrunk>0</whetherDrunk><drinkType>0</drinkType><oralAndLip>1</oralAndLip><dentition>1</dentition><pharynx>1</pharynx><leftVision>4.0</leftVision><rightVision>5.0</rightVision><leftCorrectV>0.0</leftCorrectV><rightCorrectV>0.0</rightCorrectV><hearing>1</hearing><motorFunction>1</motorFunction><eyeground>0</eyeground><skin>1</skin><sclera>1</sclera><lymphNode>1</lymphNode><barrelChest>1</barrelChest><rale>1</rale><heartRhythm>1</heartRhythm><lowerEdema>1</lowerEdema><dorsalisPulse>2</dorsalisPulse><DRE>0</DRE><breast>0</breast><cerebrovascular>1</cerebrovascular><kidneyDis>1</kidneyDis><heartDis>1</heartDis><vesselDis>1</vesselDis><eyeDis>1</eyeDis><nervousSysDis>1</nervousSysDis><otherSysDis>1</otherSysDis><healthAssess>2</healthAssess><healthAssessDesc>���������������� 6.69(mmol/L)���������������� 3.11(mmol/L)</healthAssessDesc><healthGuid>3</healthGuid><riskControl>1</riskControl><categoryList><bloodRoutines><bloodRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>01</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>6</sortId><targetId>Hb</targetId><targetName>����������                </targetName><targetResult>139                                     </targetResult><unitOfMeasure>G/L</unitOfMeasure></bloodRoutine><bloodRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>01</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>6</sortId><targetId>WBC</targetId><targetName>������                    </targetName><targetResult>6.5                                     </targetResult><unitOfMeasure>��10^9/L</unitOfMeasure></bloodRoutine><bloodRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>01</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>6</sortId><targetId>PLT</targetId><targetName>������</targetName><targetResult>145</targetResult><unitOfMeasure>��10^9/L</unitOfMeasure></bloodRoutine></bloodRoutines><urineRoutines><urineRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>02</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>10</sortId><targetId>U-PRO</targetId><targetName>������                    </targetName><targetResult>-                                       </targetResult><unitOfMeasure>-</unitOfMeasure></urineRoutine><urineRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>02</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>11</sortId><targetId>U-GLU</targetId><targetName>����                      </targetName><targetResult>-                                       </targetResult><unitOfMeasure>-</unitOfMeasure></urineRoutine><urineRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>02</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>12</sortId><targetId>U-KET</targetId><targetName>������                    </targetName><targetResult>-                                       </targetResult><unitOfMeasure>-</unitOfMeasure></urineRoutine><urineRoutine><abnormalTarget>��������������</abnormalTarget><categoryId>02</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>13</sortId><targetId>U-OB</targetId><targetName>������                    </targetName><targetResult>-                                       </targetResult><unitOfMeasure>-</unitOfMeasure></urineRoutine></urineRoutines><bloodGlucoses><bloodGlucose><abnormalTarget>��������������</abnormalTarget><categoryId>03</categoryId><categoryName>����</categoryName><referenceValue>3.9~6.1                   </referenceValue><sortId>2</sortId><targetId>Glu</targetId><targetName>��������                  </targetName><targetResult>5.54                                    </targetResult><unitOfMeasure>MMOL/L</unitOfMeasure></bloodGlucose></bloodGlucoses><microalbums/><fecalBloods><fecalBlood><abnormalTarget>��������������</abnormalTarget><categoryId>05</categoryId><categoryName>��������</categoryName><referenceValue>-                         </referenceValue><sortId>18</sortId><targetId>OB</targetId><targetName>��������                  </targetName><targetResult>0                                       </targetResult><unitOfMeasure></unitOfMeasure></fecalBlood></fecalBloods><glycatedHs/><fiveHepBs><fiveHepB><abnormalTarget>��������������</abnormalTarget><categoryId>07</categoryId><categoryName>��������</categoryName><referenceValue>-                         </referenceValue><sortId>33</sortId><targetId>HBsAg</targetId><targetName>����������������          </targetName><targetResult>0                                       </targetResult><unitOfMeasure>%</unitOfMeasure></fiveHepB></fiveHepBs><liverFuns><liverFun><abnormalTarget>��������������</abnormalTarget><categoryId>08</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>19</sortId><targetId>ALT</targetId><targetName>��������������            </targetName><targetResult>34                                      </targetResult><unitOfMeasure>U/L</unitOfMeasure></liverFun><liverFun><abnormalTarget>��������������</abnormalTarget><categoryId>08</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>20</sortId><targetId>AST</targetId><targetName>��������������            </targetName><targetResult>36                                      </targetResult><unitOfMeasure>U/L</unitOfMeasure></liverFun><liverFun><abnormalTarget>��������������</abnormalTarget><categoryId>08</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>22</sortId><targetId>TBIL</targetId><targetName>��������                  </targetName><targetResult>10.64                                   </targetResult><unitOfMeasure>��mol/L</unitOfMeasure></liverFun></liverFuns><renalFuns><renalFun><abnormalTarget>��������������</abnormalTarget><categoryId>09</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>24</sortId><targetId>Cr</targetId><targetName>��������                  </targetName><targetResult>100                                     </targetResult><unitOfMeasure>��MOL/L</unitOfMeasure></renalFun><renalFun><abnormalTarget>��������������</abnormalTarget><categoryId>09</categoryId><categoryName>������</categoryName><referenceValue>-                         </referenceValue><sortId>25</sortId><targetId>BUN</targetId><targetName>��������                  </targetName><targetResult>6.54                                    </targetResult><unitOfMeasure>��MOL/L</unitOfMeasure></renalFun></renalFuns><bloodFats><bloodFat><abnormalTarget>��������������</abnormalTarget><categoryId>10</categoryId><categoryName>����</categoryName><referenceValue>-                         </referenceValue><sortId>28</sortId><targetId>CHO</targetId><targetName>��������                  </targetName><targetResult>6.69                                    </targetResult><unitOfMeasure>��MOL/L</unitOfMeasure></bloodFat><bloodFat><abnormalTarget>��������������</abnormalTarget><categoryId>10</categoryId><categoryName>����</categoryName><referenceValue>-                         </referenceValue><sortId>29</sortId><targetId>TG</targetId><targetName>��������                  </targetName><targetResult>3.11                                    </targetResult><unitOfMeasure>��MOL/L</unitOfMeasure></bloodFat><bloodFat><abnormalTarget>��������������</abnormalTarget><categoryId>10</categoryId><categoryName>����</categoryName><referenceValue>-                         </referenceValue><sortId>31</sortId><targetId>HDL</targetId><targetName>����������������������    </targetName><targetResult>1.36                                    </targetResult><unitOfMeasure>��MOL/L</unitOfMeasure></bloodFat></bloodFats><commonTumors/><myocardials/><electrolytes/><coagulations/><others/></categoryList></healthExamReportCardDetail></res>";
	}

	private CloseableHttpClient getHttpClient() {
		return (CloseableHttpClient) this.beanFactory.getBean(CloseableHttpClient.class);
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
