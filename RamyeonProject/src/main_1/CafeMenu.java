package main_1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

//상속 및 JFrame
public class CafeMenu extends JFrame implements ActionListener {

	private static final Component CafeMenu = null;
	// 변수선언
	Container cp;
	JButton btn1, btn2, btn3, btn4, btn5; // 상부, 하부 버튼
	JPanel panel1, panel2, panel3; // 판넬 사이즈
	JLabel lbl2_1, lbl2_2, lbl2_3, lbl3_1; // 판넬2 메뉴 3개 선택, 주문 총 금액, 결제취소
	


	int x = 0; // 판넬2 중단 판넬 위치 X
	int y = 0; // 판넬2 중단 판넬 위치 Y

	int num1 = 0; // 판넬2 중단 판넬 위치 X
	int num2 = 0; // 판넬2 중단 판넬 위치 Y
	int num3 = 0; // 판넬2 중단 판넬 위치 Y


	// 상속 메서드 형성
	public CafeMenu(String title) {
		super(title);

		// 패널 생성(jFrame 객체 메소드
		cp = this.getContentPane();

		// GUI창 창크기 및 배경색 설정
		this.setBounds(800, 20, 400, 600);
		this.setBackground(new Color(255, 255, 255));

		// 디자인 메서드 호출
		panelDesign(); // 1.판넬위치
		buttonDesign(); // 2.상단 하단 버튼
		orderFrame(); // 3.주문내역 및 결제내역

		connectShop("COFFEE"); // 중단 정보는 DB조회 ( 커피를 조회 후 중단 표기 )

		// 매개 변수 값에 따라 이 창을 표시하거나 숨김 - 창을 나타낼 것이므로 true로 설정!!
		this.setVisible(true);

	}

	// 1.상단/중단/하단위치 나누기 및 각 그리드 나누기!!!!!!!!!
	public void panelDesign() {
		// 4-1. 레이아웃 형성(null 지정으로 각 각의 레이아웃을 새롭게 만들어야함.)
		this.setLayout(null);

		// 상단판넬
		panel1 = new JPanel();
		panel1.setBounds(0, 0, 400, 70);
		this.add(panel1);

		// 중간판넬
		panel2 = new JPanel();
		panel2.setBounds(20, 70, 350, 300);
		this.add(panel2);
		// 하부판넬
		panel3 = new JPanel();
		panel3.setBounds(0, 370, 400, 250);
		this.add(panel3, BorderLayout.CENTER);

	}

	// 2.상단 버튼 / 하단 버튼 생성
	public void buttonDesign() {
		panel1.setLayout(null);
		panel3.setLayout(null);

		// 상단버튼생성
		btn1 = new JButton("커피");
		btn1.setBounds(20, 10, 100, 50);
		btn1.setBackground(Color.white);
		btn1.addActionListener(this);
		panel1.add(btn1);

		btn2 = new JButton("음료");
		btn2.setBounds(145, 10, 100, 50);
		btn2.setBackground(Color.white);
		btn2.addActionListener(this);
		panel1.add(btn2);

		btn3 = new JButton("디저트");
		btn3.setBounds(270, 10, 100, 50);
		btn3.setBackground(Color.white);
		btn3.addActionListener(this);
		panel1.add(btn3);

		// 하단버튼생성
		btn4 = new JButton("결제취소");
		btn4.setBounds(20, 130, 165, 50);
		btn4.setBackground(Color.white);
		btn4.addActionListener(this);
		panel3.add(btn4);

		btn5 = new JButton("결제하기");
		btn5.setBounds(205, 130, 165, 50);
		btn5.setBackground(Color.WHITE);
		btn5.addActionListener(this);
		panel3.add(btn5);

	}

	// 3. 하단 주문내역 / 결제금액 라벨 및 폰트
	public void orderFrame() {
		panel3.setLayout(null);

		// 주문내역
		JLabel lbl2 = new JLabel("주문내역", JLabel.CENTER);
		lbl2.setFont(new FontUIResource("", Font.BOLD, 15));
		lbl2.setBounds(20, 10, 100, 70);
		lbl2.setBorder(new LineBorder(Color.black, 1));

		// 커피 선택
		lbl2_1 = new JLabel();
		lbl2_1.setHorizontalAlignment(JLabel.RIGHT);
		lbl2_1.setFont(new FontUIResource("", Font.BOLD, 11));
		lbl2_1.setBounds(119, 10, 250, 23);
		// lbl2_1.setBorder(new LineBorder(Color.black, 1));

		// 음료
		lbl2_2 = new JLabel();
		lbl2_2.setHorizontalAlignment(JLabel.RIGHT);
		lbl2_2.setFont(new FontUIResource("", Font.BOLD, 11));
		lbl2_2.setBounds(119, 33, 250, 24);
		// lbl2_2.setBorder(new LineBorder(Color.black, 1));

		// 디저트
		lbl2_3 = new JLabel();
		lbl2_3.setHorizontalAlignment(JLabel.RIGHT);
		lbl2_3.setFont(new FontUIResource("", Font.BOLD, 11));
		lbl2_3.setBounds(119, 57, 250, 23);
		// lbl2_3.setBorder(new LineBorder(Color.black, 1));

		panel3.add(lbl2);
		panel3.add(lbl2_1);
		panel3.add(lbl2_2);
		panel3.add(lbl2_3);

		// 결제금액
		JLabel lbl3 = new JLabel("총 결제금액", JLabel.CENTER);
		lbl3.setFont(new FontUIResource("", Font.BOLD, 12));
		lbl3.setBounds(20, 90, 100, 30);
		lbl3.setBorder(new LineBorder(Color.black, 1));

		// 총 결제금액 더한값
		lbl3_1 = new JLabel();
		lbl3_1.setHorizontalAlignment(JLabel.RIGHT);
		lbl3_1.setFont(new FontUIResource("", Font.BOLD, 11));
		lbl3_1.setBounds(120, 90, 250, 30);
		// lbl3_1.setBorder(new LineBorder(Color.black, 1));

		panel3.add(lbl3);
		panel3.add(lbl3_1);

	}

	// 4-1. 중단 이미지, 이름, 가격에 대한 레이아웃 만들기 -> db연결 메서드로 합쳐기
	public void centerDesign(ResultSet rs) {
		// 판넬위치 설정
		try {
			JPanel lbl1 = new JPanel(); // 이미지 센터에 정렬
			lbl1.setBounds(x, y, 110, 145);
			lbl1.setOpaque(true);

			// 아이콘 삽입
			ImageIcon icon = new ImageIcon(rs.getString("IMAGE_PATH"));
			Image image = icon.getImage();
			Image scaledImage = image.getScaledInstance(110, 100, Image.SCALE_SMOOTH); // 크기지정
			ImageIcon scaledIcon = new ImageIcon(scaledImage); // 스케일 조정

			JLabel jl1 = new JLabel();
			jl1.setIcon(scaledIcon); // 크기가 조정된 아이콘 설정
			lbl1.add(jl1);

			JLabel jl2 = new JLabel(rs.getString("C_NAME"), JLabel.CENTER);
			jl2.setPreferredSize(new Dimension(110, 10)); // 라벨 영역 크기 설정(조정)
			lbl1.add(jl2);

			JLabel jl3 = new JLabel(rs.getString("C_PRICE") + "(원)", JLabel.CENTER);
			jl3.setPreferredSize(new Dimension(110, 15)); // 라벨 영역 크기 설정
			lbl1.add(jl3);

			panel2.setLayout(null);
			panel2.add(lbl1);

			int row = rs.getInt("ROWNUM"); // db에서 번호 순서 가져와서 변수선언하기.
			String cName = rs.getString("C_NAME"); // db에서 번호 순서 가져와서 변수선언하기.
			int cPrice = rs.getInt("C_PRICE"); // db에서 번호 순서 가져와서 변수선언하기.
			String menu = rs.getString("MENU"); // db에서 번호 순서 가져와서 변수선언하기.

			if (row % 3 == 0) {
				x = 0;
				y += 155;
			} else {
				x += 120;
			}

			lbl1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					// 주문내역에 각 3개에 대한 이벤트 추가
					if ("COFFEE".equals(menu)) {
						num1 = cPrice;
						lbl2_1.setText("    " + cName + "   " + cPrice + " 원");
					} else if ("DRINGK".equals(menu)) {
						num2 = cPrice;
						lbl2_2.setText("    " + cName + "   " + cPrice + " 원");
					} else if ("DESSERT".equals(menu)) {
						num3 = cPrice;
						lbl2_3.setText("    " + cName + "   " + cPrice + " 원");
					}

					// 총 결제금액에 대한 이벤트
					int sum = num1 + num2 + num3;
					lbl3_1.setText("" + sum + "원");

				}
			});

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // db조회하는 곳에서 finally 를 썼기 때문에 생략가능..... finally 에러가 나도 무조건 실행!
	}

	// 4. centerDesign에 db불러온 데이터를 넣어서 저장 (기존 배웠던 커넥트사원 불러와서 작업 / 현 데이터에 맞게 변수와 값만
	// 변경함)
	public void connectShop(String menu) {
		Connection conn = null; // 연결 / 메서드 안에서는 초깃값 형성 안되기 때문에 null로 초깃값 설정
		Statement stmt = null; // 정보
		ResultSet rs = null; // 레코드를 자바로 가지올 때 사용
		String URL = "jdbc:oracle:thin:@localhost:1521:XE";

		String sql = "SELECT ROWNUM , C_NUM , IMAGE_PATH , C_NAME , C_PRICE , MENU from TB_KANGCAFE WHERE MENU = '"
				+ menu + "' ORDER BY C_NUM ASC";

		try {
			// 각 연결/정보/객체 가져오는 코딩작성 (try -catch해주기)
			// 연결하기
			conn = DriverManager.getConnection(URL, "teacher", "a1234");
			// 정보불러오기
			stmt = conn.createStatement();
			// 테이블 객체를 그대로 가져오기(sql문 전달)
			rs = stmt.executeQuery(sql);

			// 여러줄을 가져올 때는 while문으로 가져온다.
			// rs.next() : 다음 데이터로 이동하면서 true 반환, 더이상 데이터 없으면 false 반환
			while (rs.next()) {
				// DB로 부터 데이터 가져오기
				centerDesign(rs); // 테이블 객체를 가져와서 센터디자인에 이미지, 이름, 가격을 전달해준다.
			}
			System.out.println("오라클 드라이버 연결 성공!!!!");
			// 6. finally 및 close 작성
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("오라클 드라이버 연결 실패!!!!");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 버튼 액션주기
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		// 커피 음료 디저트 버튼에 액션주기
		if (ob == btn1 || ob == btn2 || ob == btn3) {
			// 첫번째 이미지가 오게 하기 위해서 초기화를 해줌.
			x = 0;
			y = 0;
			this.remove(panel2); // 각 버튼 누르면 panel2 지워지며 리셋됨.
			panel2 = new JPanel();
			panel2.setBounds(20, 70, 350, 300);
			this.add(panel2);

			if (ob == btn1) {
				connectShop("COFFEE");

			} else if (ob == btn2) {
				connectShop("DRINGK");

			} else if (ob == btn3) {
				connectShop("DESSERT");
			}
			panel2.revalidate(); // 컨테이너를 다시 그립니다.
			panel2.repaint(); // 컨테이너를 다시 그립니다.
		}

		// 결제취소버튼
		if (ob == btn4) {
			reset(); // 초기화
		}
		// 결제하기 창.....
		if (ob == btn5) {

			if (num1 + num2 + num3 == 0) {
				Component component = this;
				JOptionPane.showMessageDialog(component, "결제금액이 없습니다.", "결제 오류", JOptionPane.ERROR_MESSAGE);
				return; // 소스멈춤...
			}

			// 결제하기 버튼 누르면 나오는 프레임 만들기
		    JFrame oFrame = new JFrame("결제창");
			oFrame.setBounds(800, 20, 400, 600);
			// jframe 이미지넣기
			ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\승경\\OneDrive\\바탕 화면\\수업자료\\starbucksimage\\스타벅스로고-02.png");
			JLabel imageLabel1 = new JLabel(imageIcon1);
			oFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			oFrame.setContentPane(imageLabel1);
			// oFrame.getContentPane().setLayout(new FlowLayout());

			// 총 금액
			int sum = num1 + num2 + num3;

			oFrame.setLayout(null);
			JLabel lblNum1 = new JLabel("총 금액 :   " + sum, JLabel.CENTER);
			lblNum1.setFont(new Font("", Font.BOLD, 15)); // 폰트
			lblNum1.setForeground(new Color(000, 100, 000)); // 글자 컬러
			lblNum1.setBounds(105, 350, 200, 40); // 크기
			// lblNum1.setBackground(new Color(051, 102, 255)); //백그라운드 컬러
			// lblNum1.setOpaque(true); // 배경을 투명하게 설정
			oFrame.add(lblNum1);

			// 결제하기 버튼
			JButton btnAdd = new JButton("현금 결제하기");
			btnAdd.setBounds(100, 400, 200, 40);
			btnAdd.setBackground(Color.white);
			// btnAdd.addActionListener(this);
			oFrame.add(btnAdd);

			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					Component parentComponent = oFrame; // 메세지 창을 띄어주는 공간지정!!!!!
					// 가상의 결제금액
					int paymentAmount = sum;
					int amount = 0;

					// 사용자로부터 입력받은 결제금액
					String inputAmount = JOptionPane.showInputDialog(parentComponent, "결제금액을 입력하세요:", "스타벅스 결제 완료 창",
							JOptionPane.PLAIN_MESSAGE);

					// 취소버튼 아닐 때에 대해 if문을 지정!!!! 금액이 nul1이 아니라면 입력된 금액 값이 있는지 확인
					if (inputAmount != null) {
						if (inputAmount.trim().length() == 0) { // 공간 길이 확인 (입력된 값이 있는지 체크하기 위해서)
							JOptionPane.showMessageDialog(parentComponent, "금액을 입력 해주세요.", "결제 완료",
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						amount = Integer.parseInt(inputAmount); // string에서 타입 변환해주기 (int값을 입력해야하므로)
						// 결제금액이 맞는지 확인하여 boolean 값으로 반환
						boolean isPaymentCorrect = (amount >= paymentAmount); // 금액 비교해서 true, false

						// 입력한 금액이 같거나 크면 결제완료 만들기...
						if (isPaymentCorrect) {

							int changeAmount = amount - paymentAmount;
							String message = "결제가 완료되었습니다. \n거스름돈: " + changeAmount;
							JOptionPane.showMessageDialog(parentComponent, message, "결제 완료",
									JOptionPane.INFORMATION_MESSAGE);
							// 결제 하기 창 닫기
							oFrame.dispose();
							reset(); // 다시 초기화면으로 이동
							// 입력한 금액이 적으면 다시 결제
						} else {
							JOptionPane.showMessageDialog(parentComponent, "결제금액이 올바르지 않습니다.", "결제 오류",
									JOptionPane.ERROR_MESSAGE);
							return; // 실행할 실행문이 없기 때문에 중지
						}

					}
				}
			});

			oFrame.setVisible(true);
		}

	}

	//
	public void reset() {
		num1 = 0; // 커피값 초기화
		num2 = 0; // 음료값 초기화
		num3 = 0; // 디저트값 초기화

		lbl2_1.setText("");// 커피값 표기 초기화
		lbl2_2.setText("");// 음료값 표기 초기화
		lbl2_3.setText("");// 디저트값 표기 초기화
		lbl3_1.setText("");// 총값 표기 초기화
		connectShop("COFFEE");


	}

	public static void main(String[] args) {

		new CafeMenu("★스타벅스 키오스크★");

	}

}
