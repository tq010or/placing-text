package be.ugent.intec.ibcn.geo.common.io.parsers;

import be.ugent.intec.ibcn.geo.common.datatypes.DataItem;
import be.ugent.intec.ibcn.geo.common.interfaces.AbstractLineParserDataItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation for reading input data for test.
 * 
 * AbstractLineParserDataItem provides a Map of features to map the tags
 * to IDs. Input data is converted to DataItem objects with ID, lat, lon
 * and an Object [] of tags (by means of numeric IDs) after loading.
 * 
 * If the item has no tags, we still load this items, as we cannot discard this
 * item, in contrast to training data.
 * 
 * @see AbstractLineParserDataItem
 * @see LineParserTrainingItem
 * 
 * @author Olivier Van Laere <oliviervanlaere@gmail.com>
 */
public class LineParserTestItem extends AbstractLineParserDataItem{

    /**
     * Constructor.
     */
    public LineParserTestItem() {
        super();
    }

    /**
     * Actual parse implementation.
     * @param line The String input line from file
     * @return An instantiated DataItem with the test data
     */
    @Override
    public DataItem parse(String line) {
        // Prepare the return result
        DataItem item = null;
        try {
            // Parse the line
            String [] values = pattern_comma.split(line.toLowerCase());
            int id = Integer.parseInt(values[0]);
            double lat = Double.parseDouble(values[2]);
            double lon = Double.parseDouble(values[3]);
            // Split the tags
            String [] data = null;
            if (values.length >= 5)
                pattern_space.split(values[4]);
            
            // Prevent empty tags
            if (data == null || (data.length == 1 && data[0].equals("")))
                data = new String[0];
            
            // Prepare a List of selected features, by ID
            List<Integer> newdata = new ArrayList<Integer>();
            for (String s : data)
                if (features.containsKey(s))
                    newdata.add(features.get(s));
            // Get the result ready
            item = new DataItem(id, lat, lon, newdata.toArray(new Integer[0]));
        }
        catch (Exception e) {
            this.errors++;
        }
        this.processed++;
        return item;
    }
}