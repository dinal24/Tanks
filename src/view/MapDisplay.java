package view;

import gameControl.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import mapBuilder.Brick;
import mapBuilder.Coin;
import mapBuilder.DeadPlayer;
import mapBuilder.LifePack;
import mapBuilder.BasicMapBlock;
import mapBuilder.Player;
import mapBuilder.Stone;
import mapBuilder.Water;

public class MapDisplay extends JFrame implements Observer, ActionListener {

    private JPanel squaresPanel;
    private BasicMapBlock[][] map;
    private Player[] players;
    private JTable table;
    ScoreTable tbl;
    JButton btn;

    //constructor
    public MapDisplay() throws IOException {
        map = new BasicMapBlock[Game.SIZE][Game.SIZE];
        players = null;
        initComponents();
    }

    private void createSquares() throws IOException {
        LayoutManager layout = new GridLayout(Game.SIZE, Game.SIZE);
        squaresPanel = new JPanel();
        squaresPanel.setBorder(new EmptyBorder(2, 2, 2, 0));
        squaresPanel.setLayout(layout);
        for (int j = 0; j < Game.SIZE; j++) {
            for (int i = 0; i < Game.SIZE; i++) {
                JPanel squarePanel = getPanel(i, j);
                squaresPanel.add(squarePanel);
            }
        }
    }

    //create the score table
    private void createTable() {
        String[] playerColours = {"Blue", "Red", "Green", "Purple", "Yello"};
        if (players == null) {
            String columnNames[] = {"ID", "Points $", "Coins $", "Life %"};
            Object rowData[][] = {{"Player", "Points $", "Coins $", "Life Level%"},
                {"P0", "0", "0", "0"},
                {"P1", "0", "0", "0"},
                {"P2", "0", "0", "0"},
                {"P3", "0", "0", "0"},
                {"P4", "0", "0", "0"}};
            table = new NonEditableTable(rowData, columnNames);
        } else {
            for (int i = 0; i < 5; i++) {
                if (players[i] != null) {
                    Player p = players[i];
                    if (p.isOpponent()) {
                        table.setValueAt("P" + i + " [" + playerColours[i] + "]", i + 1, 0);
                    } else {
                        table.setValueAt("P" + i + " [User - " + playerColours[i] + "]", i + 1, 0);
                    }

                    table.setValueAt(p.getPoints(), i + 1, 1);
                    table.setValueAt(p.getCoins(), i + 1, 2);
                    table.setValueAt(p.getHealth(), i + 1, 3);
                }
            }
        }
    }

    private JPanel getPanel(int x, int y) {
        String freeSpace = "./src/resources/floor.png";
        String water = "./src/resources/water.png";
        String stone = "./src/resources/stone.png";
        String brick = "./src/resources/brick.png";
        String coinPile = "./src/resources/coins.png";
        String lifePack = "./src/resources/health.png";
        String p0_north = "./src/resources/tankb1_n.png";
        String p0_north_fire = "./src/resources/tankb1_nf.png";
        String p0_east = "./src/resources/tankb1_e.png";
        String p0_east_fire = "./src/resources/tankb1_ef.png";
        String p0_south = "./src/resources/tankb1_s.png";
        String p0_south_fire = "./src/resources/tankb1_sf.png";
        String p0_west = "./src/resources/tankb1_w.png";
        String p0_west_fire = "./src/resources/tankb1_wf.png";
        String p1_north = "./src/resources/tankf1_n.png";
        String p1_north_fire = "./src/resources/tankf1_nf.png";
        String p1_east = "./src/resources/tankf1_e.png";
        String p1_east_fire = "./src/resources/tankf1_ef.png";
        String p1_south = "./src/resources/tankf1_s.png";
        String p1_south_fire = "./src/resources/tankf1_sf.png";
        String p1_west = "./src/resources/tankf1_w.png";
        String p1_west_fire = "./src/resources/tankf1_wf.png";
        String p2_north = "./src/resources/tankg1_n.png";
        String p2_north_fire = "./src/resources/tankg1_nf.png";
        String p2_east = "./src/resources/tankg1_e.png";
        String p2_east_fire = "./src/resources/tankg1_ef.png";
        String p2_south = "./src/resources/tankg1_s.png";
        String p2_south_fire = "./src/resources/tankg1_sf.png";
        String p2_west = "./src/resources/tankg1_w.png";
        String p2_west_fire = "./src/resources/tankg1_wf.png";
        String p3_north = "./src/resources/tankp1_n.png";
        String p3_north_fire = "./src/resources/tankp1_nf.png";
        String p3_east = "./src/resources/tankp1_e.png";
        String p3_east_fire = "./src/resources/tankp1_ef.png";
        String p3_south = "./src/resources/tankp1_s.png";
        String p3_south_fire = "./src/resources/tankp1_sf.png";
        String p3_west = "./src/resources/tankp1_w.png";
        String p3_west_fire = "./src/resources/tankp1_wf.png";
        String p4_north = "./src/resources/tanky1_n.png";
        String p4_north_fire = "./src/resources/tanky1_nf.png";
        String p4_east = "./src/resources/tanky1_e.png";
        String p4_east_fire = "./src/resources/tanky1_ef.png";
        String p4_south = "./src/resources/tanky1_s.png";
        String p4_south_fire = "./src/resources/tanky1_sf.png";
        String p4_west = "./src/resources/tanky1_w.png";
        String p4_west_fire = "./src/resources/tanky1_w_f.png";


        ImagePanel panel;
        BasicMapBlock obj = map[x][y];
        if (obj == null) {
            panel = new ImagePanel(freeSpace);
        } else if (obj instanceof Stone) {
            panel = new ImagePanel(stone);
        } else if (obj instanceof Brick) {
            panel = new ImagePanel(brick);
        } else if (obj instanceof Water) {
            panel = new ImagePanel(water);
        } else if (obj instanceof Coin) {
            panel = new ImagePanel(coinPile);
        } else if (obj instanceof LifePack) {
            panel = new ImagePanel(lifePack);
        } else if (obj instanceof Player && !(obj instanceof DeadPlayer)) {
            Player player = (Player) obj;
            if (player.getName().equals("P0")) {
                if (player.getDirection() == 0) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p0_north_fire);
                    } else {
                        panel = new ImagePanel(p0_north);
                    }

                } else if (player.getDirection() == 1) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p0_east_fire);
                    } else {
                        panel = new ImagePanel(p0_east);
                    }
                } else if (player.getDirection() == 2) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p0_south_fire);
                    } else {
                        panel = new ImagePanel(p0_south);
                    }
                } else {
                    if (player.isShot()) {
                        panel = new ImagePanel(p0_west_fire);
                    } else {
                        panel = new ImagePanel(p0_west);
                    }
                }
            } else if (player.getName().equals("P1")) {
                if (player.getDirection() == 0) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p1_north_fire);
                    } else {
                        panel = new ImagePanel(p1_north);
                    }
                } else if (player.getDirection() == 1) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p1_east_fire);
                    } else {
                        panel = new ImagePanel(p1_east);
                    }
                } else if (player.getDirection() == 2) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p1_south_fire);
                    } else {
                        panel = new ImagePanel(p1_south);
                    }
                } else {
                    if (player.isShot()) {
                        panel = new ImagePanel(p1_west_fire);
                    } else {
                        panel = new ImagePanel(p1_west);
                    }
                }
            } else if (player.getName().equals("P2")) {
                if (((Player) obj).getDirection() == 0) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p2_north_fire);
                    } else {
                        panel = new ImagePanel(p2_north);
                    }
                } else if (player.getDirection() == 1) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p2_east_fire);
                    } else {
                        panel = new ImagePanel(p2_east);
                    }
                } else if (player.getDirection() == 2) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p2_south_fire);
                    } else {
                        panel = new ImagePanel(p2_south);
                    }
                } else {
                    if (player.isShot()) {
                        panel = new ImagePanel(p2_west_fire);
                    } else {
                        panel = new ImagePanel(p2_west);
                    }
                }
            } else if (player.getName().equals("P3")) {
                if (((Player) obj).getDirection() == 0) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p3_north_fire);
                    } else {
                        panel = new ImagePanel(p3_north);
                    }
                } else if (player.getDirection() == 1) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p3_east_fire);
                    } else {
                        panel = new ImagePanel(p3_east);
                    }
                } else if (player.getDirection() == 2) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p3_south_fire);
                    } else {
                        panel = new ImagePanel(p3_south);
                    }
                } else {
                    if (player.isShot()) {
                        panel = new ImagePanel(p3_west_fire);
                    } else {
                        panel = new ImagePanel(p3_west);
                    }
                }
            } else {
                if (player.getDirection() == 0) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p4_north_fire);
                    } else {
                        panel = new ImagePanel(p4_north);
                    }
                } else if (player.getDirection() == 1) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p4_east_fire);
                    } else {
                        panel = new ImagePanel(p4_east);
                    }
                } else if (player.getDirection() == 2) {
                    if (player.isShot()) {
                        panel = new ImagePanel(p4_south_fire);
                    } else {
                        panel = new ImagePanel(p4_south);
                    }
                } else {
                    if (player.isShot()) {
                        panel = new ImagePanel(p4_west_fire);
                    } else {
                        panel = new ImagePanel(p4_west);
                    }
                }
            }
//        } else if ((obj instanceof DeadPlayer) && (((DeadPlayer) obj).getCoins() > 0)) {
//            panel = new ImagePanel(coinPile);
//        } 
        }else {
            panel = new ImagePanel(freeSpace);
        }
        return panel;
    }

    //initiate the map componants
    private void initComponents() throws IOException {

        /*the cell width is 50 pix*/
        int boardSize = (int) ((int) ImagePanel.getImageSize() * Game.SIZE * 1.1);

        createSquares();
        
        btn = new JButton("View Scores");
         btn.setBounds(6, 4, 2, 3);

         
        //createTable();>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DIER : GAME ARENA");
        getContentPane().add(squaresPanel, BorderLayout.CENTER);
        getContentPane().add(btn,BorderLayout.PAGE_START);
        btn.addActionListener(this);
        //getContentPane().add(table, BorderLayout.AFTER_LAST_LINE);>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        setLocationByPlatform(true);
        setVisible(true);
        Dimension preferredSize = new Dimension();
        preferredSize.width = boardSize;
        preferredSize.height = boardSize + 100;
        setPreferredSize(preferredSize);
        setResizable(false);
        pack();
        tbl = new ScoreTable();
        tbl.setVisible( true );
    }

    public void actionPerformed(ActionEvent event) {
       tbl.setVisible( true );
    }
    
    //update the map according to the observable class object (game)
    public void update(Observable o, Object arg) {
        if (o instanceof Game) {
            map = Game.getInstance().getMap();
            players = Game.getInstance().getPlayers();
            try {
                getContentPane().remove(squaresPanel);
                createSquares();
                //createTable();>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                tbl.createTable(players);
                getContentPane().add(squaresPanel, BorderLayout.CENTER);
                setVisible(true);
                repaint();
            } catch (IOException ex) {
                Logger.getLogger(MapDisplay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MapDisplay map = new MapDisplay();
                } catch (IOException ex) {
                    Logger.getLogger(MapDisplay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
