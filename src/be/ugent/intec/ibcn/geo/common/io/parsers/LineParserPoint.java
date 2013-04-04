package be.ugent.intec.ibcn.geo.common.io.parsers;

import be.ugent.intec.ibcn.geo.common.datatypes.Point;
import be.ugent.intec.ibcn.geo.common.interfaces.LineParser;
import java.util.regex.Pattern;

/**
 * TODO Add comment
 * @author Olivier Van Laere <oliviervanlaere@gmail.com>
 */
public abstract class LineParserPoint implements LineParser {
    
    /**
     * Precompiled regex space split pattern significantly boosts performance.
     * (compared to regular string.split method). Stored here as it is commonly
     * used by parser implementations.
     */
    protected Pattern pattern_space = Pattern.compile(" ");
    
    /**
     * Precompiled regex comma split pattern significantly boosts performance.
     * (compared to regular string.split method). Stored here as it is commonly
     * used by parser implementations.
     */
    protected Pattern pattern_comma = Pattern.compile(",");

    protected int processed = 0;

    @Override
    public int getProcessed() {
        return this.processed;
    }
    
    protected int errors = 0;
    
    @Override
    public int getErrors() {
        return this.errors;
    }
    
    @Override
    public abstract Point parse(String line);

}