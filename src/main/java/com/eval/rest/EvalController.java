package com.eval.rest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.eval.dto.EvalRequest;
import com.eval.dto.EvalResponse;
import com.eval.util.CountChecker;
import com.eval.util.CountGetter;

@RestController
@RequestMapping(path="/counter-api")
public class EvalController {

	@Autowired
	CountChecker countChecker;
	
	@Autowired
	CountGetter countGetter;
	
    @RequestMapping(path="/search", method=RequestMethod.POST)
    public EvalResponse getWordCount(@RequestBody EvalRequest request) {
    	EvalResponse response;
		
    	try {
			response = countChecker.getCount(request);
		} catch (Exception e) {
			response = new EvalResponse();
			response.setStatus("error");
			
			// technical details added only for PoC
			// add business error message here.
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
    }
    
    @RequestMapping(path="/top/{topCount}")
    @ResponseBody
    public String getTopCount(HttpServletResponse servletResponse, 
    		@PathVariable("topCount") String topCount) {
    	String response;
		
    	servletResponse.setContentType("text/plain; charset=utf-8");
    	
    	try {
    		response = countGetter.readParaAndReturnCount(Integer.parseInt(topCount));
		} catch (Exception e) {
			response = "error";			
		}
		
		return response;
    }
}
