package main_1;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//1. 상속 및 JFrame
public class RamyeonMenu extends JFrame {

	// 1-2. 변수선언
	Container cp;
	JButton btn1, btn2, btn3, btn4, btn5; // 상부, 하부 버튼
	JPanel panel1, panel2, panel3; // 판넬 사이즈
	JLabel lbl1; // 하단 주문,결제라벨

	// 2. 상속 메서드 형성
	public RamyeonMenu(String title) {
		super(title);

		// 2-1. 패널 생성(jFrame 객체 메소드
		cp = this.getContentPane();

		// 2-2. GUI창 창크기 및 배경색 설정
		this.setBounds(800, 100, 400, 600);
		this.setBackground(new Color(255, 255, 255));

		// 디자인 메서드 호출
		panelDesign(); // 판넬위치
		topDesign(); // 상단 버튼 3개
		orderFrame();
		underDesign();

		// 3. 매개 변수 값에 따라 이 창을 표시하거나 숨김 - 창을 나타낼 것이므로 true로 설정!!
		this.setVisible(true);

	}

	// 4.상단/중단/하단위치
	public void panelDesign() {
		// 4-1. 레이아웃 형성(null 지정으로 각 각의 레이아웃을 새롭게 만들어야함.)
		this.setLayout(null);
		// 4-2. 상단 위치
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 400, 70);
		panel1.setBackground(Color.GREEN);
		// panel.setOpaque(true);
		this.add(panel1);

		panel2 = new JPanel();
		panel2.setBounds(0, 70, 400, 300);
		panel2.setBackground(Color.orange);
		// panel.setOpaque(true);
		this.add(panel2);

		panel3 = new JPanel();
		panel3.setBounds(0, 370, 400, 250);
		panel3.setBackground(Color.blue);
		// panel.setOpaque(true);
		this.add(panel3);

	}

	// 5.상단 버튼
	public void topDesign() {
		// 4-3. 버튼 생성 및 이름 (상부 라면종류, 토핑, 사이드 버튼 생성하기)
		// 0, 0, 400, 50
		panel1.setLayout(null);
		btn1 = new JButton("라면종류");
		btn1.setBounds(20, 10, 100, 50);
		btn1.setBackground(Color.white);
		panel1.add(btn1);

		btn2 = new JButton("토핑");
		btn2.setBounds(145, 10, 100, 50);
		btn2.setBackground(Color.white);
		panel1.add(btn2);

		btn3 = new JButton("사이드");
		btn3.setBounds(270, 10, 100, 50);
		btn3.setBackground(Color.white);
		panel1.add(btn3);

	}

	// 6. 하단 주문내역 + 결제금액 프레임
	public void orderFrame() {
		panel3.setLayout(null);
		// 0, 400, 400, 200
		lbl1 = new JLabel("주문내역 + 결제");
		lbl1.setBounds(20, 10, 350, 110);
		lbl1.setBorder(new LineBorder(Color.black, 2));
		panel3.add(lbl1);

	}
	
	//7. 하단 전체취소 및 결제하기버튼 생성
	public void underDesign() {
		panel3.setLayout(null);
		
		btn4 = new JButton("결제취소");
		btn4.setBounds(20, 130, 165, 50);
		btn4.setBackground(Color.white);
		panel3.add(btn4);
		
		btn5 = new JButton("결제하기");
		btn5.setBounds(205, 130, 165, 50);
		btn5.setBackground(Color.WHITE);
		panel3.add(btn5);
		
	}

	public static void main(String[] args) {
		// 3-1. 창 생성하기 (상단 이름 설정까지)
		new RamyeonMenu("라면 키오스크 주문하기");

	}
}
