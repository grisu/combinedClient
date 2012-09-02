package start;
import java.lang.reflect.Method;
import java.util.Arrays;


public class Client {

	public static void main(String[] args) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String clientName = null;

		if ( (args == null) || (args.length == 0) ) {
			clientName = "template";
		} else {
			clientName = args[0];
		}

		Class clientToStart = null;

		if ("gricli".equalsIgnoreCase(clientName)) {
			clientToStart = grisu.gricli.Gricli.class;
		} else if ("template".equalsIgnoreCase(clientName)) {
			clientToStart = grisu.frontend.view.swing.GrisuTemplateApp.class;
		} else if ("virtscreen".equalsIgnoreCase(clientName)) {
			clientToStart = org.bestgrid.virtscreen.view.GrisuVirtScreen.class;
		} else if ("grid-tray".equalsIgnoreCase(clientName)) {
			clientToStart = grith.gridsession.view.tray.GridSessionTrayClient.class;
		} else if ("grid-session".equalsIgnoreCase(clientName)) {
			clientToStart = grith.gridsession.CliSessionControl.class;
		} else if ("ssh-setup".equalsIgnoreCase(clientName)) {
			clientToStart = grisu.frontend.view.swing.utils.ssh.SshKeyCopyFrame.class;
		}

		String[] args_new = new String[] {};
		if (args.length > 1) {
			args_new = Arrays.copyOfRange(args, 1, args.length);
		}

		try {
			Method initMethod = clientToStart.getDeclaredMethod("main",
					String[].class);

			initMethod.invoke(null, (Object) args_new);

		} catch (Exception e) {
			System.err
			.println("Can't start client: " + e.getLocalizedMessage());
			System.exit(1);
		}

	}


}
