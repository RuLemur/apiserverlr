package server.helpers;

/**
 * Created by RuLemur on 22.07.2018 in 21:29.
 * testSegmento
 */
public class TempConverter {

    public enum Units {
        CELSIUS, FAHRENHEIT, KELVIN
    }

    public static short convert(Units from, Units to, short fromTemp)  {
        if (from.equals(Units.CELSIUS)) {
            if (to.equals(Units.CELSIUS))
                return fromTemp;
            if (to.equals(Units.FAHRENHEIT))
                return (short) (fromTemp * 1.8 + 32);
            if (to.equals(Units.KELVIN))
                return (short) (fromTemp + 273.15);
        }
        if (from.equals(Units.FAHRENHEIT)) {
            if (to.equals(Units.FAHRENHEIT))
                return fromTemp;
            if (to.equals(Units.CELSIUS))
                return (short) ((fromTemp - 32) / 1.8);
            if (to.equals(Units.KELVIN))
                return (short) ((fromTemp - 32) / 1.8 + 273.15);
        }
        if (from.equals(Units.KELVIN)) {
            if (to.equals(Units.KELVIN))
                return fromTemp;
            if (to.equals(Units.FAHRENHEIT))
                return (short) ((fromTemp - 273.15) * 1.8 + 32);
            if (to.equals(Units.CELSIUS))
                return (short) (fromTemp - 273.15);
        }
        return 0;
//        throw new Exception("Ощибка перевода велечин: Неизвестная велечина");
    }
}
