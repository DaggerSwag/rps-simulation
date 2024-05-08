import javax.swing.JLabel;

public class CustomLabels{
    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;    
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    private JLabel label;    
    private int x_pos;
    private int y_pos;
    private String symbol;

    public CustomLabels(JLabel label, int x_pos, int y_pos, String symbol){
        this.label=label;
        this.x_pos=x_pos;
        this.y_pos=y_pos;
        this.symbol=symbol;
    }
}
