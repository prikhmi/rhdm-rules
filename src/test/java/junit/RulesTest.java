package junit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.avis.drools.RevenueSegmentationData;

/**
 * This is a sample class to test some rules.
 */
public class RulesTest {
	static KieBase kbase;
	static KieSession ksession;
	static KieRuntimeLogger klogger;
	static String inputpathfile;
	static String outputpathfile;
	static RulesDataProcess rdp;
	static String xml;

	@BeforeClass
	public static void setupKsession() {
		try {
			// load up the knowledge base and create session
			ksession = readKnowledgeBase();
			System.out.println("setupKsession() ksession  = " + ksession);
			//klogger = KieServices.Factory.get().getLoggers().newFileLogger(ksession, "resource/ruleslogger");
			rdp = new RulesDataProcess();
			inputpathfile = System.getProperty("user.dir") + "\\resource" + "\\input" + "\\RulesInput.xml";
			outputpathfile = System.getProperty("user.dir") + "\\resource" + "\\output" + "\\RulesOutput.xml";
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@Test

	public void RuleTest1() throws JsonParseException, JsonMappingException, IOException {
		RevenueSegmentationData revenueSegmentationData = (RevenueSegmentationData) rdp.ReadXMLInput(inputpathfile,
				RevenueSegmentationData.class);
		System.out.println("Priya Java object = " + revenueSegmentationData);
		System.out.println("-----------------------------input starts--------------------------------");

		// xml = rdp.printConsoleInput(revenueSegmentationData);
		System.out.println(rdp.printConsoleInput(revenueSegmentationData));

		System.out.println("-----------------------------input Ends--------------------------------");
		FactHandle revenueSegmentationDataFH = ksession.insert(revenueSegmentationData);

		ksession.fireAllRules();
		
		rdp.writeXMLOutput(revenueSegmentationData, outputpathfile);

		System.out.println("-----------------------------Output starts--------------------------------");

		// xml = rdp.printConsoleInput(revenueSegmentationData);
		System.out.println(rdp.printConsoleInput(revenueSegmentationData));

		System.out.println("-----------------------------Output Ends--------------------------------");

		ksession.delete(revenueSegmentationDataFH);

	}

	@AfterClass
	public static void closeKsession() {
		try {
			// closing resources
			//klogger.close();
			ksession.dispose();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KieSession readKnowledgeBase() throws Exception {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession();
		return kSession;
	}

}