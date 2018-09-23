package core;

import misc.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;


// SoSObjectManager에 등록해야(register() 함수 호출) update, render 함수 실행됨
// 등록 해지하면 실행 X
public abstract class SoSObject {

    SoSObject parent;
    public void setParent(SoSObject parent) {
        this.parent = parent;
    }

    Queue<SoSObject> children = new LinkedList<>();
    public void addChild(SoSObject child) {
        child.setParent(this);
        children.add(child);
    }
    public void removeChild(SoSObject child) {
        children.remove(child);
    }

    // << Field: position >>
    // 타일 좌표
    private Position position = new Position();
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }
    public Position getPosition() {
        return position;
    }
    // << Field: position >>


    // << Field: currentImage >>
    SoSImage currentImage;
    public void setCurrentImage(SoSImage currentImage) {
        this.currentImage = currentImage;
    }

    public SoSImage getCurrentImage() {
        return currentImage;
    }
    // << Field: currentImage >>


    // << Field: canUpdate >>
    // 이 필드가 true일 때만 SoSObject Update
    boolean _canUpdate = true;
    public void canUpdate(boolean _canUpdate) {
        this._canUpdate = _canUpdate;
    }
    // << Field: canUpdate >>


    // << Field: canRender >>
    // 이 필드가 true일 때만 SoSObject render
    boolean _visible = true;
    public void visible(boolean _visible) {
        this._visible = _visible;
    }
    // << Field: canRender >>


    // << Method: start >>
    // 초기화 이후(init) update 이전 동작 코드 작성
    public void start() { }
    // << Method: start >>


    // << Method: update >>
    // 매 프레임마다 호출
    // 상속 불가능. 외부용
    public final void update() {
        if(_canUpdate) {
            onUpdate();

            children.add(null);
            while(true) {
                SoSObject child = children.poll();
                if(child == null) break;
                child.update();
                children.add(child);
            }
        }
    }

    // 상속 가능, 내부용
    protected void onUpdate() {

    }
    // << Method: update >>


    // << Method: render >>
    // 매 프레임마다 호출
    // 상속 불가능. 외부용
    public final void render(Graphics2D g) {
        if(_visible) {

            Graphics2D localGraphic = (Graphics2D)g.create();
            localGraphic.translate(position.x, position.y);
            onRender(localGraphic);

            children.add(null);
            while(true) {
                SoSObject child = children.poll();
                if(child == null) break;
                child.render(localGraphic);
                children.add(child);
            }
        }
    }

    // 상속 가능, 내부용
    protected void onRender(Graphics2D g) {
    }
    // << Method: render >>


    // << Method: clear >>
    // 정리 코드 작성
    public void clear() {
        if(parent != null) {
            parent.removeChild(this);
            parent = null;
        }
        children.forEach(child -> child.clear());
    }
    // << Method: clear >>


    // 자기 자신 삭제
    public void remove() {
        clear();
    }
}
