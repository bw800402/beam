/*
 * Copyright (c) 2013, European Bioinformatics Institute (EMBL-EBI)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package uk.ac.ebi.grins;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of valid OpenSMILES elements.
 *
 * @author John May
 */
enum Element {

    /** Unspecified/Unknown element */
    Unknown("*"),

    Hydrogen("H"),
    Helium("He"),

    Lithium("Li"),
    Beryllium("Be"),
    Boron("B", true, 3),
    Carbon("C", true, 4),
    Nitrogen("N", true, 3, 5),
    Oxygen("O", true, 2),
    Fluorine("F", false, 1),
    Neon("Ne"),

    Sodium("Na"),
    Magnesium("Mg"),
    Aluminum("Al"),
    Silicon("Si"),
    Phosphorus("P", true, 3, 5),
    Sulfur("S", true, 2, 4, 6),
    Chlorine("Cl", false, 1),
    Argon("Ar"),

    Potassium("K"),
    Calcium("Ca"),
    Scandium("Sc"),
    Titanium("Ti"),
    Vanadium("V"),
    Chromium("Cr"),
    Manganese("Mn"),
    Iron("Fe"),
    Cobalt("Co"),
    Nickel("Ni"),
    Copper("Cu"),
    Zinc("Zn"),
    Gallium("Ga"),
    Germanium("Ge"),
    Arsenic("As", true),
    Selenium("Se", true),
    Bromine("Br", false, 1),
    Krypton("Kr"),

    Rubidium("Rb"),
    Strontium("Sr"),
    Yttrium("Y"),
    Zirconium("Zr"),
    Niobium("Nb"),
    Molybdenum("Mo"),
    Technetium("Tc"),
    Ruthenium("Ru"),
    Rhodium("Rh"),
    Palladium("Pd"),
    Silver("Ag"),
    Cadmium("Cd"),
    Indium("In"),
    Tin("Sn"),
    Antimony("Sb"),
    Tellurium("Te"),
    Iodine("I", false, 1),
    Xenon("Xe"),

    Cesium("Cs"),
    Barium("Ba"),
    Lutetium("Lu"),
    Hafnium("Hf"),
    Tantalum("Ta"),
    Tungsten("W"),
    Rhenium("Re"),
    Osmium("Os"),
    Iridium("Ir"),
    Platinum("Pt"),
    Gold("Au"),
    Mercury("Hg"),
    Thallium("Tl"),
    Lead("Pb"),
    Bismuth("Bi"),
    Polonium("Po"),
    Astatine("At"),
    Radon("Rn"),

    Francium("Fr"),
    Radium("Ra"),
    Lawrencium("Lr"),
    Rutherfordium("Rf"),
    Dubnium("Db"),
    Seaborgium("Sg"),
    Bohrium("Bh"),
    Hassium("Hs"),
    Meitnerium("Mt"),
    Darmstadtium("Ds"),
    Roentgenium("Rg"),
    Copernicium("Cn"),
    Flerovium("Fl"),
    Livermorium("Lv"),

    Lanthanum("La"),
    Cerium("Ce"),
    Praseodymium("Pr"),
    Neodymium("Nd"),
    Promethium("Pm"),
    Samarium("Sm"),
    Europium("Eu"),
    Gadolinium("Gd"),
    Terbium("Tb"),
    Dysprosium("Dy"),
    Holmium("Ho"),
    Erbium("Er"),
    Thulium("Tm"),
    Ytterbium("Yb"),

    Actinium("Ac"),
    Thorium("Th"),
    Protactinium("Pa"),
    Uranium("U"),
    Neptunium("Np"),
    Plutonium("Pu"),
    Americium("Am"),
    Curium("Cm"),
    Berkelium("Bk"),
    Californium("Cf"),
    Einsteinium("Es"),
    Fermium("Fm"),
    Mendelevium("Md"),
    Nobelium("No");

    /** The symbol of the element. */
    private final String symbol;

    /**
     * Default valence information - only present if the atom is part of the
     * organic subset.
     */
    private final int[] valence;

    /** Whether the element can be aromatic. */
    private final boolean aromatic;

    /** Look up of elements by symbol */
    private static final Map<String, Element> elementMap
            = new HashMap<String, Element>();

    static {
        for (Element element : values()) {
            if (element.aromatic())
                elementMap.put(element.symbol().toLowerCase(), element);
            elementMap.put(element.symbol(), element);
        }
    }

    private Element(String symbol) {
        this(symbol, false, null);
    }

    private Element(String symbol,
                    boolean aromatic) {
        this(symbol, aromatic, null);
    }

    private Element(String symbol,
                    boolean aromatic,
                    int... valence) {
        this.symbol = symbol;
        this.valence = valence;
        this.aromatic = aromatic;
    }

    /**
     * Access the symbol of the element.
     *
     * @return element symbol
     */
    String symbol() {
        return symbol;
    }

    /**
     * Can the element be aromatic (as defined by the OpenSMILES
     * specification).
     *
     * @return whether the element may be aromatic
     */
    boolean aromatic() {
        return aromatic;
    }

    /**
     * Is the element a member of the organic subset and can be written without
     * brackets. If the element is both organic and aromatic is a member of the
     * aromatic subset and can still be written without brackets.
     *
     * @return the element can be written without brackets
     */
    boolean organic() {
        return valence != null;
    }

    /**
     * Determine the number of implied hydrogens an organic (or aromatic) subset
     * atom has based on it's bond order sum. The valances for the organic
     * elements (B, C, N, O, P, S, F, Cl, Br and I) are defined in the
     * OpenSMILES specification.
     *
     * @param sum bond order sum
     * @return the number of implied hydrogens
     * @throws IllegalArgumentException the element was not a member of the
     *                                  organic subset and did not have default
     *                                  valence information
     */
    int implicitHydrogens(int sum) {
        if (!organic())
            throw new IllegalArgumentException("inorganic atom, no preset valence");

        for (final int v : valence)
            if (sum <= v) return v - sum;

        // bond order sum exceeds or equals maximum valance
        return 0;
    }

    /**
     * Given an element symbol, provide the element for that symbol. If no
     * symbol was found then null is returned.
     *
     * @param symbol the element symbol
     * @return element for the symbol, or null if none found
     */
    static Element ofSymbol(final String symbol) {
        return elementMap.get(symbol);
    }
}