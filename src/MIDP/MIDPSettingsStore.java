/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.ISettingsStore;
import java.util.Hashtable;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;


public class MIDPSettingsStore implements ISettingsStore {

    private RecordStore records = null;
    private Hashtable keyIndexes;

    public MIDPSettingsStore()
    {
        OpenStore();
    }

    public void Dispose() {
        if (records == null) return;

        try {
            records.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        records = null;
        keyIndexes = null;
    }

    public String getSetting(String key, String defaultValue)
    {
        if (records == null) return defaultValue;

        String data = defaultValue;
        try {
            Integer keyIndex = (Integer)keyIndexes.get(key);
            if (keyIndex == null) return defaultValue;

            data = new String(records.getRecord(keyIndex.intValue()));

            data = data.substring(data.indexOf('=')+1);

        } catch (RecordStoreException ex) {
        }
        return data;
    }

    public void setSetting(String key, String value)
    {
        if (records == null) return;

        try {
            Integer keyIndex = (Integer)keyIndexes.get(key);
            String data = key + "=" + value;
            if (keyIndex == null)
            {
                keyIndex = new Integer(records.addRecord(data.getBytes(), 0, data.length()));
                keyIndexes.put(key, keyIndex);
            }
            else
            {
                records.setRecord(keyIndex.intValue(), data.getBytes(), 0, data.length());
            }
            data = new String(records.getRecord(keyIndex.intValue()));

            data = data.substring(data.indexOf('=')+1);

        } catch (RecordStoreException ex) {
        }
    }

    private void OpenStore() {

        if (records == null) return;
        try {
            records = RecordStore.openRecordStore("catcher", true);

            keyIndexes = new Hashtable(records.getNumRecords());

            RecordEnumeration re = records.enumerateRecords(null, null, false);
            while (re.hasNextElement())
            {
                String data = new String(re.nextRecord());

                String key = data.substring(0, data.indexOf('=')-1);
                int id = re.previousRecordId();

                keyIndexes.put(key, new Integer(id));
            }

        } catch (RecordStoreException ex) {
            return;
        }

    }

}
