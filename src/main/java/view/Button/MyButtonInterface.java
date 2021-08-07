package view.Button;

import model.ModelExpression;
import view.keyboard.KeyboardPublisher;

import java.awt.event.ActionListener;

public interface MyButtonInterface extends ActionListener, KeyboardPublisher, Clickable {
    ModelExpression getMyModel();
    void setModel(ModelExpression model);
}
