class Token {
  
    public final static int ERROR = 0;
    public final static int OPENTAG = 1;
    public final static int CLOSEDTAG = 2;
    public final static int WORD = 3;
    public final static int NUM = 4;
    public final static int PUNCT = 5;
    public final static int HYPHEN = 6;
    public final static int APOST = 7;

    public int m_type;
    public String m_value;
    public int m_line;
    public int m_column;
    
    Token (int type, String value, int line, int column) {
        m_type = type;
        m_value = value;
        m_line = line;
        m_column = column;
    }

    public String toString() {
        switch (m_type) {
            case OPENTAG:
                return "OPEN-" + m_value;
            case CLOSEDTAG:
                return "CLOSE-" + m_value;
            case WORD:
                return "WORD(" + m_value + ")";
            case NUM:
                return "NUMBER(" + m_value + ")";
            case PUNCT:
                return "PUNCTUATION(" + m_value + ")";
            case HYPHEN:
                return "HYPHENATED(" + m_value + ")";
            case APOST:
                return "APOSTROPHIZED(" + m_value + ")";
            case ERROR:
                return "ERROR(" + m_value + ")";
            default:
                return "UNKNOWN(" + m_value + ")";
        }
    }
}

