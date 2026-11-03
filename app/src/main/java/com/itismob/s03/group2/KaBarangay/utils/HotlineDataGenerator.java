package com.itismob.s03.group2.KaBarangay.utils;

import com.itismob.s03.group2.KaBarangay.models.Hotline;
import java.util.ArrayList;
import java.util.List;

public class HotlineDataGenerator {

    public static List<Hotline> getHotlines() {
        List<Hotline> list = new ArrayList<>();

        list.add(new Hotline(
                "PANGGABING TANOD",
                "Barangay Tanod Outpost",
                "24/7 barangay patrol, community disturbance, at agarang panggabing saklolo sa bawat purok.",
                "Bgy Desk",
                "Direktang Numero:",
                "8-700-112",
                android.R.drawable.ic_secure
        ));

        list.add(new Hotline(
                "MEDIKAL AT AMBULANSYA",
                "Bgy Health Center & EMU",
                "First aid, ambulansya, panganganak, at agarang medikal na tulong para sa mga residente.",
                "Medikal",
                "Linya ng Sentro:",
                "[Numero: Medikal Desk]",
                android.R.drawable.ic_menu_add
        ));

        list.add(new Hotline(
                "PAMATAY-SUNOG",
                "BFP Substation (Sunog)",
                "Alerto sa sunog, chemical spills, vehicular rescue, at technical extraction operations.",
                "BFP 160",
                "Pambansang Hotline:",
                "160 • [BFP Estasyon]",
                android.R.drawable.ic_dialog_alert
        ));

        list.add(new Hotline(
                "PULISYA AT KAAYUSAN",
                "PNP Police Precinct",
                "Krimen, malubhang aksidente, banta sa buhay, at agarang pagpapatupad ng batas.",
                "PNP 117",
                "Estasyon / Hotline:",
                "117 / 911 • [Presinto 4]",
                android.R.drawable.ic_secure
        ));

        list.add(new Hotline(
                "KALAMIDAD AT RESCUE",
                "BDRRMC Disaster Desk",
                "Baha, bagyo, lindol, evacuation assistance, at rescue operations sa panahon ng sakuna.",
                "BDRRMC",
                "Operasyon Desk:",
                "[Numero: BDRRMC OpCen]",
                android.R.drawable.ic_menu_help
        ));

        return list;
    }
}