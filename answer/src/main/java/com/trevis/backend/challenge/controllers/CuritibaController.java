package com.trevis.backend.challenge.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.dto.CuritibaRegister;
import com.trevis.backend.challenge.dto.CuritibaRegisterResult;
import com.trevis.backend.challenge.services.CPFValidator;
import com.trevis.backend.challenge.services.CityValidator;

@RestController
public class CuritibaController {

	@Autowired
	private CityValidator cepValiator;

	@Autowired
	private CPFValidator cpfValidator;

	@GetMapping("/curitiba")
	public CuritibaRegisterResult curitiba(CuritibaRegister input) {

		ArrayList<String> messages = new ArrayList<>();

		if (input.cep() == null || input.cep().length() != 8 || !isNumeric(input.cep())) {
			messages.add("cep is not valid.");	
		}
		else if (!cepValiator.validate(input.cep(), "Curitiba")) {
			messages.add("The cep is not from Curitiba.");
		}
		
		String cpf = 
			input.cpf() == null ? "" :
			input.cpf()
				.replace(".", "")
				.replace("-", "");
		if (!cpfValidator.validate(cpf)) {
			messages.add("cpf is not valid.");
		}
		
		if (messages.size() == 0) {
			return new CuritibaRegisterResult(true, 
				"cpf and cep are both valid and cep is from Curitiba."
			);
		}

		String message = "";
		for (var msg : messages)
			message += msg + "\n";

		return new CuritibaRegisterResult(true, message);
	}

	boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}

		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}
}