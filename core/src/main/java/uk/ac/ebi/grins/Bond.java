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

/**
 * Enumeration of valid connections between atoms. The connections include all
 * the valid undirected and directed bond types and {@link #DOT}. Opposed to the
 * other types, {@link #DOT} indicates that two atoms are not connected. <p/>
 *
 * @author John May
 */
public enum Bond {

    /** Atoms are not bonded. */
    DOT(".", 0),

    /** Atoms are bonded by either a single or aromatic bond. */
    IMPLICIT("", 0) {
        @Override public int electrons() {
            throw new IllegalArgumentException("unknown number of electrons in implied bond");
        }
    },

    /** Atoms are bonded by a single pair of electrons. */
    SINGLE("-", 2),

    /** Atoms are bonded by two pairs of electrons. */
    DOUBLE("=", 4),

    /** Atoms are bonded by three pairs of electrons. */
    TRIPLE("#", 6),

    /** Atoms are bonded by four pairs of electrons. */
    QUADRUPLE("$", 8),

    /** Atoms are bonded by a delocalized bond of an aromatic system. */
    AROMATIC(":", 3),

    /**
     * Directional, single or aromatic bond (currently always single). The bond
     * is relative to each endpoint such that the second endpoint is
     * <i>above</i> the first or the first end point is <i>below</i> the
     * second.
     */
    UP("/", 2) {
        @Override public Bond inverse() {
            return DOWN;
        }
    },

    /**
     * Directional, single or aromatic bond (currently always single). The bond
     * is relative to each endpoint such that the second endpoint is
     * <i>below</i> the first or the first end point is <i>above</i> the
     * second.
     */
    DOWN("\\", 2) {
        @Override public Bond inverse() {
            return UP;
        }
    };

    /** The symbol for the bond in the SMILES grammar. */
    private final String symbol;

    /** The total number of electrons shared, i.e. not the number of pairs. */
    private final int electrons;

    private Bond(String symbol, int electrons) {
        this.symbol = symbol;
        this.electrons = electrons;
    }


    /**
     * The symbol of the bond in the SMILES grammar.
     *
     * @return bond symbol
     */
    public final String symbol() {
        return symbol;
    }

    /**
     * The total number electrons shared between atoms.
     *
     * @return number of electrons
     */
    public int electrons() {
        return electrons;
    }

    /**
     * Access the inverse of a directional bond ({@link #UP}, {@link #DOWN}). If
     * a bond is non-directional the same bond is returned.
     *
     * @return inverse of the bond
     */
    public Bond inverse() {
        return this;
    }

    /** @inheritDoc */
    public final String toString() {
        return symbol;
    }
}