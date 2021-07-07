import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUIAula implements ActionListener, ListSelectionListener {

	int totalAmount = 0;
	int handleDepositValue = 10;

	// Inicializa os componentes do JavaSwing
	private JButton depositButton;
	private JLabel textAmount;
	private JList depositValuesList;
	private JProgressBar depositProgressBar;

	public GUIAula() {

		// Inicializa um componente lista com valores de R$ 10 a R$ 50.
		String depositValues[] = { "R$ 10", "R$ 20", "R$ 30", "R$ 40", "R$ 50" };
		depositValuesList = new JList(depositValues);
		// Seta o index do valor selecionado por default.
		depositValuesList.setSelectedIndex(0);
		// Seta o listener de alterações para nossa própria classe.
		depositValuesList.addListSelectionListener(this);

		// Inicializa um componente botão com o texto "Deposit".
		depositButton = new JButton("Deposit");
		// Seta o listener de clique para nossa própria classe.
		depositButton.addActionListener(this);
		// Seta uma ação a ser disparada quando o botão for clicado.
		depositButton.setActionCommand("deposit_act");

		// Inicializa um componente barra de progresso.
		depositProgressBar = new JProgressBar();
		depositProgressBar.setStringPainted(true);
		// Seta o valor inicial 0.
		depositProgressBar.setValue(0);
		// Seta o tamanho da barra.
		depositProgressBar.setSize(100, 25);

		// Inicializa um componente de texto com o valor "Amount: R$ 0".
		textAmount = new JLabel("Amount: R$ 0");

		// Inicializa um componente de panel.
		JPanel panel = new JPanel();
		// Cria margens em todas as direções.
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		// Seta GridLayout em nosso panel. (Existe uma variedade de layout que podem ser
		// utilizados)
		panel.setLayout(new GridLayout(0, 1));
		// Adiciona todos os nossos componentes ao painel.
		panel.add(depositValuesList);
		panel.add(depositButton);
		panel.add(depositProgressBar);
		panel.add(textAmount);

		// Inicializa a janela da GUI.
		JFrame frame = new JFrame();
		frame.add(panel);
		// Seta o titulo da janela.
		frame.setTitle("GUI Aula");
		// Seta o tamanho da janela.
		frame.setSize(300, 500);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new GUIAula();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		// Caso a ação "deposit_act" seja detecada no clique de algum botão.
		if ("deposit_act".equals(command)) {
			new Thread() {

				public void run() {
					for (int i = 0; i <= 100; i++) {
						depositProgressBar.setValue(i);

						try {
							Thread.sleep(20);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					totalAmount += handleDepositValue;
					textAmount.setText("Amount: R$ " + totalAmount);

					depositProgressBar.setValue(0);

				}

			}.start();

		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		/*
		 * Aqui criamos uma função, que é acionada todas as vezes que uma nova célula da
		 * lista é clicada. Quando isso acontece, o valor da célula é capturado,
		 * convertido e colocado em nossa variável handleDepositValue.
		 */
		String selectedOption = (String) depositValuesList.getSelectedValue();
		String selectedValue = selectedOption.substring(3, 5);

		int amountSelected = Integer.parseInt(selectedValue);

		handleDepositValue = amountSelected;
	}

}
