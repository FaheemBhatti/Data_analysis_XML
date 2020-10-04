package de.itdesign.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import de.itdesign.application.bean.City;
import de.itdesign.application.bean.Operation;
import de.itdesign.application.bean.Result;
import de.itdesign.application.helper.XmlHelper;

public class XMLCalculator {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// Don't change this part
		if (args.length == 3) {
			// Path to the data file, e.g. data/data.xml
			final String DATA_FILE = args[0];
			// Path to the data file, e.g. operations/operations.xml
			final String OPERATIONS_FILE = args[1];
			// Path to the output file
			final String OUTPUT_FILE = args[2];

			XmlHelper xmlHelper = XmlHelper.newInstance();

			List<Operation> operations = xmlHelper.readOpearations(OPERATIONS_FILE);
			
			List<Result> results = new ArrayList<Result>();

			for (Operation operation : operations) {
				String type = operation.getType();
				String func = operation.getFunc();
				String name = operation.getName();

				List<City> citiesList = xmlHelper.readData(DATA_FILE, operation);
				
				switch (func) {
				case "min":

					Double min = citiesList.stream().map(city -> {
						double value = 0;

						if (type.equalsIgnoreCase("sub")) {
							value = city.getArea();
						} else {
							value = city.getAttrib();
						}
						return value;
					}).min((c1, c2) -> {
						return (int) (c1 - c2);
					}).get();

					results.add(new Result(name, min));
					break;
				case "max":

					Double max = citiesList.stream().map(city -> {
						double value = 0;

						if (type.equalsIgnoreCase("sub")) {
							value = city.getArea();
						} else {
							value = city.getAttrib();
						}
						return value;
					}).max((c1, c2) -> {
						return (int) (c1 - c2);
					}).get();

					results.add(new Result(name, max));

					break;
				case "sum":
					DoubleSummaryStatistics collect = citiesList.stream().map(c -> {
						double value = 0;

						if (type.equalsIgnoreCase("sub")) {
							value = c.getArea();
						} else {
							value = c.getAttrib();
						}
						return value;
					}).collect(Collectors.summarizingDouble(Double::doubleValue));

					results.add(new Result(name, collect.getSum()));

					break;
				case "average":

					DoubleSummaryStatistics cc = citiesList.stream().map(c -> {
						double value = 0;

						if (type.equalsIgnoreCase("sub")) {
							value = c.getArea();
						} else {
							value = c.getAttrib();
						}
						return value;
					}).collect(Collectors.summarizingDouble(Double::doubleValue));

					results.add(new Result(name, cc.getAverage()));
				}

			}

			xmlHelper.saveResult(results, OUTPUT_FILE);
			
		} else {
			System.exit(1);
		}
	}
}
