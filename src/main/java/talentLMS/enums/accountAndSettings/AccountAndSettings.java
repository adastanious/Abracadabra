package talentLMS.enums.accountAndSettings;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

/**
 @author Dastan Aidarov
 */

public enum AccountAndSettings {

    SITE_NAME ("aidas"),
    SETTINGS_ERROR_TEXT_COLOR ("#b94a48"),
    SITE_NAME_ERROR_TEXT ("'Site name' cannot exceed 40 characters"),
    SITE_DESCRIPTION_ERROR_TEXT ("'Site description' cannot exceed 255 characters"),
    BASIC_SETTINGS_SAVED_SUCCESSFULLY ("Basic settings updated successfully"),
    USERS_SETTINGS_SAVED_SUCCESSFULLY ("Users settings updated successfully"),
    NO_MATCHES_FOUND ("No matches found"),
    LANGUAGES (List.of ("English", "Español (Spanish)", "Deutsch (German)",
            "Français (French)", "Ελληνικά (Greek)",
            "Italiano (Italian)", "Pусский (Russian)", "Suomi (Finnish)", "Српски (Serbian)", "Українська (Ukrainian)",
            "Català (Catalan)", "Latviešu (Latvian)", "Čeština (Czech)", "Slovenščina (Slovenian)",
            "Slovenský (Slovak)", "Türkçe (Turkish)", "Azərbaycan (Azerbaijani)", "हिंदी (Hindi)",
            "Português (Portuguese)", "Melayu (Malay - Malaysia)", "简体字 (Chinese Simplified)",
            "繁體字 (Chinese Traditional)", "한국어 (Korean)", "Nederlands (Dutch)", "日本語 (Japanese)",
            "Tiếng Việt (Vietnamese)", "Hrvatski (Croatian)", "ქართული (Georgian)",
            "босански (Bosnian - Bosnia and Herzegovina)", "עברית (Hebrew)",
            "العربية (Arabic - United Arab Emirates)", "Lietuvių kalba (Lithuanian)", "Eesti [beta] (Estonian)",
            "فارسی [beta] (Persian - Iran)", "Magyar nyelv [beta] (Hungarian)", "Svenska [beta] (Swedish)",
            "Norsk [beta] (Norwegian)", "հայերէն [beta] (Armenian)", "монгол хэл [beta] (Mongolian)",
            "Dansk [beta] (Danish)", "Polski [beta] (Polish)", "Bahasa Indonesia [beta] (Indonesian)",
            "ภาษาไทย [beta] (Thai)", "Português Brasileira [beta] (Portuguese Brazil)",
            "Română [beta] (Romanian)")),

    TIME_ZONES (List.of("(GMT -01:00) Azores", "(GMT -01:00) Cape Verde", "(GMT -02:00) Atlantic islands",
            "(GMT -02:00) Greenland", "(GMT -03:00) Buenos Aires, Georgetown, Brasilia", "(GMT -03:00) Montevideo, Uruguay",
            "(GMT -03:00) Recife, Araguaina, Brazil", "(GMT -03:00) Santiago, Chile", "(GMT -03:00) Santiago, Chile", "(GMT -03:00) Sao Paulo, Brazil",
            "(GMT -04:00) Canada/Atlantic (Atlantic Standard Time)", "(GMT -04:00) Caracas, Venezuela", "(GMT -04:00) Guyana",
            "(GMT -04:00) Halifax, Nova Scotia, Canada / Hamilton,Bermuda", "(GMT -04:00) La Paz, Bolivia", "(GMT -04:00) Puerto Rico", "(GMT -04:30) Newfoundland",
            "(GMT -05:00) Bogotá, Quito", "(GMT -05:00) Eastern Time (US & Canada)", "(GMT -05:00) Indiana (East), USA", "(GMT -05:00) Lima, Peru",
            "(GMT -05:00) Montreal, Quebec, Toronto, Ontario, Canada", "(GMT -05:00) Panama", "(GMT -06:00) Central Time (US & Canada)", "(GMT -06:00) Chihuahua, Mexico",
            "(GMT -06:00) Costa Rica", "(GMT -06:00) El Salvador", "(GMT -06:00) Guatemala", "(GMT -06:00) Managua, Nicaragua", "(GMT -06:00) Mexico City",
            "(GMT -06:00) Monterrey, Mexico", "(GMT -06:00) Regina, Saskatchewan, Canada", "(GMT -06:00) Tegucigalpa, Honduras", "(GMT -06:00) Winnipeg",
            "(GMT -07:00) Arizona / Mountain Time, no DST (US & Canada)", "(GMT -07:00) Edmonton, Canada", "(GMT -07:00) Mazatlan", "(GMT -07:00) Mountain Time (US & Canada)",
            "(GMT -08:00) Pacific Time (US & Canada)", "(GMT -08:00) Tijuana, Baja California, Mexico", "(GMT -08:00) Vancouver, Canada", "(GMT -09:00) Alaska",
            "(GMT -09:00) Juneau City, Borough, Alaska", "(GMT -10:00) Hawaii", "(GMT -11:00) Midway Islands, Samoa", "(GMT -11:00) Pago Pago", "(GMT +00:00) Dublin, Ireland",
            "(GMT +00:00) Greenwich Mean Time: Edinburgh, Lisbon, London", "(GMT +00:00) Lisbon, Portugal", "(GMT +00:00) Nouakchott, Mauritania",
            "(GMT +00:00) Reykjavik, Iceland", "(GMT +00:00) UTC", "(GMT +01:00) Algiers, Algeria", "(GMT +01:00) Amsterdam, Netherlands",
            "(GMT +01:00) Belgrade, Bratislava, Ljubljana", "(GMT +01:00) Berlin, Germany", "(GMT +01:00) Brussels, Belgium", "(GMT +01:00) Budapest, Hungary",
            "(GMT +01:00) Casablanca, Morocco", "(GMT +01:00) Copenhagen, Denmark", "(GMT +01:00) Luxembourg", "(GMT +01:00) Madrid, Spain", "(GMT +01:00) Oslo, Norway",
            "(GMT +01:00) Paris, Vilnius, West Central Africa", "(GMT +01:00) Prague, Czech Republic", "(GMT +01:00) Rome, Italy", "(GMT +01:00) Stockholm, Sweden",
            "(GMT +01:00) Tunis, Tunisia", "(GMT +01:00) Vienna, Austria", "(GMT +01:00) Warsaw, Poland", "(GMT +01:00) West Central Africa",
            "(GMT +01:00) Zurich, Switzerland", "(GMT +02:00) Athens, Greece", "(GMT +02:00) Beirut, Lebanon", "(GMT +02:00) Bucharest, Romania",
            "(GMT +02:00) Cairo, Egypt", "(GMT +02:00) Harare, Zimbabwe / Pretoria, South Africa", "(GMT +02:00) Helsinki, Finland", "(GMT +02:00) Jerusalem",
            "(GMT +02:00) Johannesburg, South Africa", "(GMT +02:00) Khartoum, Sudan", "(GMT +02:00) Kiev, Ukraine", "(GMT +02:00) Nicosia, Cyprus",
            "(GMT +02:00) Sofia, Bulgaria", "(GMT +02:00) Tripoli, Libya", "(GMT +03:00) Aden", "(GMT +03:00) Amman, Jordan", "(GMT +03:00) Baghdad, Iraq",
            "(GMT +03:00) Bahrain", "(GMT +03:00) Damascus, Syria", "(GMT +03:00) Djibouti", "(GMT +03:00) Istanbul, Turkey", "(GMT +03:00) Kuwait",
            "(GMT +03:00) Minsk, Belarus", "(GMT +03:00) Mogadishu, Somalia", "(GMT +03:00) Moscow, St. Petersburg, Volgograd", "(GMT +03:00) Nairobi, Kenya",
            "(GMT +03:00) Qatar", "(GMT +03:00) Riyadh, Saudi Arabia", "(GMT +03:30) Tehran, Iran", "(GMT +04:00) Baku, Azerbaijan", "(GMT +04:00) Dubai",
            "(GMT +04:00) Muscat, Oman", "(GMT +04:30) Kabul", "(GMT +05:00) Islamabad, Tashkent", "(GMT +05:00) Karachi, Pakistan",
            "(GMT +05:00) Yekaterinburg, Russia", "(GMT +05:30) Mumbai, Kolkata, Madras, New Delhi", "(GMT +05:45) Kathmandu, Nepal", "(GMT +06:00) Almaty",
            "(GMT +06:00) Astana, Dhaka", "(GMT +06:30) Rangoon, Myanmar", "(GMT +07:00) Bangkok, Thailand", "(GMT +07:00) Jakarta, Indonesia",
            "(GMT +07:00) Krasnoyarsk, Russia", "(GMT +07:00) Novosibirsk, Russia", "(GMT +07:00) Vietnam", "(GMT +08:00) Beijing, Shanghai, China",
            "(GMT +08:00) Hong Kong, Urumqi", "(GMT +08:00) Irkutsk, Russia / Ulaanbaatar, Mongolia", "(GMT +08:00) Kuala Lumpur, Malaysia",
            "(GMT +08:00) Perth, Australia", "(GMT +08:00) Singapore", "(GMT +08:00) Taipei, Taiwan", "(GMT +09:00) Osaka, Sapporo, Tokyo, Japan",
            "(GMT +09:00) Seoul, South Korea", "(GMT +09:00) Yakutsk, Russia", "(GMT +09:30) Darwin, Australia", "(GMT +10:00) Brisbane, Australia",
            "(GMT +10:00) Guam / Port Moresby, Papua New Guinea", "(GMT +10:00) Vladivostok, Russia", "(GMT +10:30) Adelaide, Australia",
            "(GMT +11:00) Canberra, Melbourne, Sydney, Australia", "(GMT +11:00) Hobart, Tasmania", "(GMT +11:00) Magadan, Russia / Solomon Islands",
            "(GMT +11:00) New Caledonia", "(GMT +12:00) Eniwetok, Kwajalein", "(GMT +12:00) Fiji, Marshall Islands", "(GMT +12:00) Kamchatka, Russia",
            "(GMT +13:00) Apia, Samoa", "(GMT +13:00) Auckland, Wellington, New Zealand")),

    DATE_FORMATS (List.of("DD/MM/YYYY", "MM/DD/YYYY", "YYYY/MM/DD")),
    TIME_FORMATS (List.of("24-hour", "12-hour")),
    CURRENCIES (List.of("US Dollar", "Canadian Dollar", "Australian Dollar",
            "New Zealand Dollar", "Singapore Dollar", "Hong Kong Dollar", "Taiwan New Dollar", "Japanese Yen", "Brazilian Real",
            "Mexican Peso", "Euro", "UK Pound", "Danish Krone", "Norwegian Krone", "Swedish Krona", "Swiss Franc", "Hungarian Forint",
            "Turkish Lira", "Russian Ruble", "Polish Zloty", "Czech Koruna", "Indian Rupee", "Philippine Peso", "Thai Baht",
            "Malaysian Ringgit", "Israeli New Shekel")),

    SECTION_BASIC_SETTINGS ("Basic settings"),
    SECTION_USERS ("Users"),
    SECTION_GAMIFICATION ("Gamification"),
    SECTION_DOMAIN ("Domain"),

    USERS_DEFAULT_USER_TYPE (List.of("SuperAdmin", "Admin-Type", "Trainer-Type", "Learner-Type")),
    USERS_VISIBLE_USER_FORMATS (List.of("D. AIDAROV", "DAS A.", "DAS AIDAROV", "AIDAROV D.", "AIDAROV DAS", "DASAQA")),
    DOMAIN_NAME_NEW ("komo"),
    DOMAIN_NAME_EXISTING ("amazon"),
    DOMAIN_NAME_INVALID ("digital_nomads"),
    NEW_URL ("https://komo.talentlms.com/index"),
    DOMAIN_NAME_CURRENT ("abracadabra"),
    DOMAIN_NAME_EMPTY (""),

    DOMAIN_EXISTING_ERROR_TEXT ("This value already exists for 'Domain name'"),
    DOMAIN_IS_REQUIRED_ERROR_TEXT ("'Domain name' is required"),
    DOMAIN_IS_NOT_VALID_ERROR_TEXT ("This is not a valid 'Domain name'"),
    DASHBOARD_URL ("https://abracadabra.talentlms.com/dashboard/index");

    private List<String> list;

    AccountAndSettings(List<String> list) {
        this.list = list;
    }

    private String string;

    AccountAndSettings(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
