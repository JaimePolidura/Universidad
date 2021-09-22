package ProgramacionII.microndas.programas.grill;

import ProgramacionII.microndas.programas.selectores.SelectorGrill;

/**
 * De esta menera hago que solo halla una sola instancia del selectorGrill
 * Y en las clases programas que necesite el selector grill implemento esto y ya.
 */
public interface IncluyeSelectorGrill {
    SelectorGrill selectorGrill = new SelectorGrill();
}
