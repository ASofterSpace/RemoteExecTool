/**
 * Unlicensed code created by A Softer Space, 2019
 * www.asofterspace.com/licenses/unlicense.txt
 */
package com.asofterspace.remoteExecTool;

import com.asofterspace.toolbox.configuration.ConfigFile;
import com.asofterspace.toolbox.io.Directory;
import com.asofterspace.toolbox.io.File;
import com.asofterspace.toolbox.io.Record;
import com.asofterspace.toolbox.Utils;

import java.util.List;

import javax.swing.SwingUtilities;


public class Main {

	public final static String PROGRAM_TITLE = "RemoteExecTool";
	public final static String VERSION_NUMBER = "0.0.0.2(" + Utils.TOOLBOX_VERSION_NUMBER + ")";
	public final static String VERSION_DATE = "22. June 2019 - 23. June 2019";

	public static void main(String[] args) {

		// let the Utils know in what program it is being used
		Utils.setProgramTitle(PROGRAM_TITLE);
		Utils.setVersionNumber(VERSION_NUMBER);
		Utils.setVersionDate(VERSION_DATE);

		if (args.length > 0) {
			if (args[0].equals("--version")) {
				System.out.println(Utils.getFullProgramIdentifierWithDate());
				return;
			}

			if (args[0].equals("--version_for_zip")) {
				System.out.println("version " + Utils.getVersionNumber());
				return;
			}
		}

		System.out.println("Loading configuration...");

		ConfigFile config = new ConfigFile("config.json");

		Integer id = config.getInteger("id");

		String name = config.getValue("name");

		String sharePath = config.getValue("sharePath");

		List<Record> commands = config.getAllContents().getArray("commands");

		for (Record command : commands) {
			System.out.println(command);
			System.out.println(command.getString("path"));
		}

		// use actual discovery (by everyone when starting up writing to the share that they exist,
		// and then all others reading from there and finding it) instead of configuration
		List<Record> others = config.getAllContents().getArray("others");

		System.out.println("This is the RemoteExecTool with id " + id + " on computery device " + name + "! :)");

		SwingUtilities.invokeLater(new GUI(id, name, sharePath, commands, others));
	}

}
