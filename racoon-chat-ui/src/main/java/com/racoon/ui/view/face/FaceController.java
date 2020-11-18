package com.racoon.ui.view.face;

import com.racoon.ui.view.UIObject;
import com.racoon.ui.view.achat.ChatInit;
import com.racoon.ui.view.achat.IChatEvent;
import com.racoon.ui.view.achat.IChatMethod;

public class FaceController extends FaceInit implements IFaceMethod {
    private FaceView faceView;
    public  FaceController(UIObject obj, ChatInit chatInit, IChatEvent chatEvent, IChatMethod chatMethod) {
        super(obj);
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;
        this.chatMethod = chatMethod;
    }

    @Override
    public void initView() {
        faceView = new FaceView(this);
    }

    @Override
    public void initEventDefine() {
        new FaceEventDefine(this);
    }

    @Override
    public void doShowFace(Double x, Double y) {
        setX(x + 230*(1-0.618));
        setY(y-160);
        show();
    }
}
