package me.eophantom.damagetools;

import me.eophantom.damagetools.gui.DamageAmountGui;
import me.eophantom.damagetools.gui.DamageConfigGui;
import me.eophantom.damagetools.gui.DamageExceptionGui;
import me.eophantom.damagetools.gui.ManageExceptionGUI;
import me.eophantom.damagetools.listeners.DamageListener;

public class Setter {

    private DamageExceptionGui damageExceptionGui;
    private DamageAmountGui damageAmountGui;
    private DamageConfigGui damageConfigGui;
    private DamageListener damageListener;
    private ManageExceptionGUI manageExceptionGUI;

    public ManageExceptionGUI getManageExceptionGUI() {
        return manageExceptionGUI;
    }

    public DamageListener getdamageListener() {return damageListener;}

    public DamageConfigGui getConfig() {
        return damageConfigGui;
    }

    public DamageExceptionGui getException() {
        return damageExceptionGui;
    }

    public DamageAmountGui getAmount() {
        return damageAmountGui;
    }

    public void setConfig(DamageConfigGui damageConfigGui) {
        this.damageConfigGui = damageConfigGui;
    }

    public void setException(DamageExceptionGui damageExceptionGui) {
        this.damageExceptionGui = damageExceptionGui;
    }

    public void setAmount(DamageAmountGui damageAmountGui) {
        this.damageAmountGui = damageAmountGui;
    }

    public void setDamageListener(DamageListener damageListener) {
        this.damageListener = damageListener;
    }

    public void setManageExceptionGUI(ManageExceptionGUI manageExceptionGUI) {
        this.manageExceptionGUI = manageExceptionGUI;
    }
}
