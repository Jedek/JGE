package model;

/**
 *
 */
public abstract class Pawn {

    /**
     * Hieronder worden de attributen gedefineerd
     */
    protected FieldArea currentArea;

    /**
     * Pion is de constructor en zet de image
     * @param imagePad
     */
    protected Pawn()
    {
    }

    /**
     * setHuidigeVak zet het huidge vak van de pion
     * @param vak refereert naar het vak object
     */
    public void setCurrentArea(FieldArea area)
    {
        currentArea = area;
    }

    public FieldArea getCurrentArea()
    {
        return currentArea;
    }

    /**
     * getVakPixelY return de attribuut pixelY van object Vak
     * @return pixelY van object vak
     */
    protected int getCurrentAreaPixelY()
    {
        return currentArea.getPixelY();
    }

    /**
     * getVakPixelX return de attribuut pixelX van object Vak
     * @return pixelX van object vak
     */
    protected int getCurrentAreaPixelX()
    {
        return currentArea.getPixelX();
    }

    /**
     * verplaatsPionNaarVak methode die ervoor zorgt dat de pion wordt verplaats van A naar B
     */
    protected void movePawnToArea(FieldArea area){
        setCurrentArea(area);
    }
}