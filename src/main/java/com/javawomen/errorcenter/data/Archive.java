package com.javawomen.errorcenter.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.javawomen.errorcenter.controller.dto.LogDto;

public class Archive {

	public Archive() {
	}

	public void write(LogDto dto, String archiveName) throws IOException {

		String diretorio = "datalog/";

		try {
			FileWriter fw = new FileWriter(diretorio + archiveName + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);

			// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd"); //
			// format(formatter));
			bw.write(dto.getCreatedAt().toString());
			bw.write(",");
			bw.write(dto.getOrigin());
			bw.write(",");
			bw.write(dto.getDescription());
			bw.write(",");
			bw.write(dto.getLevelName());
			bw.write(",");
			bw.write(dto.getEnvironmentName());
			bw.write(",");
			bw.write(dto.getDetail());
			bw.close();

		} catch (Exception ex) {
			throw new IOException("Falha ao tentar executar o método write de Arquive.class", ex);
		}
	}

	public LogDto read(String archiveName) throws Throwable {
		String diretorio = "datalog/";
		Scanner scanner = new Scanner(new File(diretorio + archiveName), "UTF-8");
		LogDto logDto = new LogDto();
		try {
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();

				Scanner linhaScanner = new Scanner(linha);
				linhaScanner.useLocale(Locale.US);// regras para valor 0.0
				linhaScanner.useDelimiter(",");// regex

				String date = linhaScanner.next();
				String origin = linhaScanner.next();
				String description = linhaScanner.next();
				String levelName = linhaScanner.next();
				String environmentName = linhaScanner.next();
				String detail = linhaScanner.next();
				
				LocalDateTime parsedDate = LocalDateTime.parse(date);

				logDto.setCreatedAt(parsedDate);
				logDto.setLevelName(levelName);
				logDto.setEnvironmentName(environmentName);
				logDto.setOrigin(origin);
				logDto.setDescription(description);
				logDto.setDetail(detail);
				linhaScanner.close();
			}
			scanner.close();
		} catch (Exception ex) {
			throw new IOException("Falha ao tentar executar o método read de Arquive.class", ex);
		}
		return logDto;
	}

	public List<LogDto> readArchive() throws Throwable {
		List<LogDto> dto = new ArrayList<>();
		File arquivos[];
		File diretorio = new File("datalog");
		try {
			arquivos = diretorio.listFiles();
			for (int i = 0; i < arquivos.length; i++) {
				dto.add(read(arquivos[i].getName()));
			}
		} catch (Throwable t) {
			throw new IOException("Falha ao tentar executar o método readArchive de Arquive.class", t);
		}

		return dto;
	}
}
