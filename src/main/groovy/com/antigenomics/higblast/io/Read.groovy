/*
 * Copyright 2013-2015 Mikhail Shugay (mikhail.shugay@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antigenomics.higblast.io

import com.antigenomics.higblast.Util

class Read {
    final String header, seq, qual

    Read(String header, String seq, String qual) {
        this.header = header
        this.seq = seq
        this.qual = qual
    }

    Read(String header, String seq) {
        this(header, seq, Util.MAX_QUAL_SYMBOL * seq.length())
    }

    Read getRc() {
        new Read(header, Util.revCompl(seq), qual.reverse())
    }

    byte qualAt(int pos) {
        qual ? ((int) (qual.charAt(pos)) - Util.QUAL_OFFSET) : Util.MAX_QUAL
    }

    @Override
    String toString() {
        header + "\n" + seq + "\n+\n" + qual
    }

    @Override
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Read read = (Read) o

        return header == read.header && seq == read.seq && qual == read.qual
    }

    @Override
    int hashCode() {
        int result = header.hashCode()
        result = 31 * result + seq.hashCode()
        31 * result + qual.hashCode()
    }
}
