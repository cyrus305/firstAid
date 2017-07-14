package com.iaid.webservice.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")

public class Responses<T> {
	 /*
    * This would carry the request id if there was one in the request
    */
   protected Integer status;

   /*
    * This would always contain a human-friendly message
    */
   protected String message;

   /*
    * The payload for the response if there is any.
    */
   protected T data;
   
   protected Integer startNo;
   protected Integer endNo;
   protected Integer currentNo;

   /*
    * The HTTP status for the response if there is any.
    */
//   protected Integer httpStatus;

   /**
    * All went well, and (usually) some data was returned.
    * 
    * @param <T> generic param
    * @param pData data to be sent
    * @param pMessage message
    * @return <T> response generic
    */
   public static <T> Responses<T> ok(Integer pStatus, T pData, String pMessage) {
       Responses<T> response = new Responses<T>();
       response.status = pStatus;
       response.data = pData;
       response.message = pMessage;
//       response.httpStatus = HttpStatus.ACCEPTED_202.value();
       return response;
   }
   
   public static <T> Responses<T> ok(Integer pStatus, T pData, String pMessage,
									Integer pCurrentNo,Integer pEndNo) {
		Responses<T> response = new Responses<T>();
		response.status = pStatus;
		response.data = pData;
		response.message = pMessage;
		
		/*int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());*/
		if (pCurrentNo > 0 && pEndNo > 0 ){
			response.startNo = Math.max(1, pCurrentNo-5);
			response.endNo = Math.min(response.startNo + 10, pEndNo);
			response.currentNo = pCurrentNo;
		}else {
			response.startNo = 0;
			response.endNo = 0;
			response.currentNo = 0;
		}
		//response.httpStatus = HttpStatus.ACCEPTED_202.value();
		return response;
	}
   
   /**
    * There was a problem with the data submitted, or some pre-condition of the API call wasn't satisfied
    * 
    * @param <T> generic param
    * @param pMessage message
    * @param pHttpStatus http status
    * @return <T> response
    */
   public static <T> Responses<T> fail(String pMessage, Integer pHttpStatus) {
       Responses<T> response = new Responses<T>();
       response.message = pMessage;
//       response.httpStatus = pHttpStatus;
       return response;
   }

   /**
    * An error occurred in processing the request, i.e. an exception was thrown
    * 
    * @param <T> generic param
    * @param pMessage message
    * @param pHttpStatus http status
    * @return <T> response
    */
   public static <T> Responses<T> error(String pMessage, Integer pHttpStatus) {
       Responses<T> response = new Responses<T>();
       response.message = pMessage;
       response.status = pHttpStatus;
       return response;
   }

   /**
    * 
    * @param pMessage message param
    */
   public void setSuccessMessage(String pMessage) {
       message = pMessage;
   }

   
   public Integer getStatus() {
	return status;
}

	public void setStatus(Integer status) {
		this.status = status;
	}

/**
    * @return the message
    */
   public String getMessage() {
       return message;
   }

   /**
    * @param messageparam the message to set
    */
   public void setMessage(String messageparam) {
       this.message = messageparam;
   }

   /**
    * @return the data
    */
   public T getData() {
       return data;
   }

   /**
    * @param dataparam the data to set
    */
   public void setData(T dataparam) {
       this.data = dataparam;
   }

	public Integer getStartNo() {
		return startNo;
	}
	
	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}
	
	public Integer getEndNo() {
		return endNo;
	}
	
	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
	
	public Integer getCurrentNo() {
		return currentNo;
	}
	
	public void setCurrentNo(Integer currentNo) {
		this.currentNo = currentNo;
	}

   /**
    * @return the httpStatus
    */
   /*public Integer getHttpStatus() {
       return httpStatus;
   }*/

   /**
    * @param pHttpStatus the httpStatus to set
    */
   /*public void setHttpStatus(Integer pHttpStatus) {
       this.httpStatus = pHttpStatus;
   }*/
   
   
}