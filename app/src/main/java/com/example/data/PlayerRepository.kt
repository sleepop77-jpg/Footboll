package com.example.data

import com.example.R
import com.example.model.*

object PlayerRepository {

    val players: List<Player> = listOf(
        // 1. Lionel Messi
        Player(
            id = "messi",
            name = "Lionel Messi",
            displayName = "L. Messi",
            number = 10,
            position = PlayerPosition.RW,
            club = "Inter Miami CF",
            clubBadgeColor = 0xFFF472B6,
            league = "Major League Soccer",
            nationality = "Argentina",
            nationalityCode = "AR",
            flagEmoji = "🇦🇷",
            isProminent = true,
            prominentBadge = "8x Ballon d'Or Winner",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1518091043644-c1d4457512c6?w=600&auto=format&fit=crop&q=80",
            overallRating = 93,
            formRating = 9.15f,
            marketValueEur = "€30M",
            contractUntil = "December 2025",
            preferredFoot = "Left",
            attributes = PlayerAttributes(
                pace = 80,
                shooting = 91,
                passing = 94,
                dribbling = 95,
                defending = 34,
                physical = 65,
                vision = 98,
                composure = 99
            ),
            careerTotals = CareerTotals(
                totalAppearances = 1098,
                totalGoals = 850,
                totalAssists = 385,
                totalMinutes = 88400,
                passAccuracyPct = 84.8f,
                shotConversionPct = 21.4f,
                duelsWonPct = 57.2f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Inter Miami", "MLS", 25, 23, 16, 8.84f, listOf("Supporters' Shield")),
                SeasonRecord("2023/24", "Inter Miami", "Leagues Cup", 22, 18, 12, 8.70f, listOf("Leagues Cup Champion")),
                SeasonRecord("2022/23", "PSG", "Ligue 1", 41, 21, 20, 8.28f, listOf("Ligue 1", "FIFA World Cup Champion")),
                SeasonRecord("2021/22", "PSG", "Ligue 1", 34, 11, 15, 7.85f, listOf("Ligue 1")),
                SeasonRecord("2020/21", "Barcelona", "La Liga", 47, 38, 14, 8.52f, listOf("Copa del Rey", "Pichichi")),
                SeasonRecord("2019/20", "Barcelona", "La Liga", 44, 31, 27, 8.71f, listOf("Pichichi (25G, 21A)"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 8,
                championsLeague = 4,
                worldCup = 1,
                continentalCup = 2, // Copa America 2021, 2024
                domesticLeagueTitles = 12,
                domesticCups = 7,
                goldenBoots = 6,
                notableHonors = listOf(
                    "FIFA World Cup Golden Ball (2014, 2022)",
                    "Laureus World Sportsman of the Year (2020, 2023)",
                    "All-time record 91 goals in a single calendar year (2012)",
                    "Finalissima Winner (2022)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2004", "Senior Barcelona Debut", "Came on as a substitute against Espanyol at age 17.", "DEBUT"),
                CareerMilestone("2009", "First Ballon d'Or & Historic Sextuple", "Won all 6 trophies in a single calendar year with Pep Guardiola.", "TROPHY"),
                CareerMilestone("2012", "World Record 91 Goals", "Shattered Gerd Müller's all-time calendar year goal record.", "RECORD"),
                CareerMilestone("2021", "Copa América Triumph in Maracanã", "Ended Argentina's 28-year senior international title drought.", "TROPHY"),
                CareerMilestone("2022", "FIFA World Cup Champion in Qatar", "Scored twice in the final vs France and lifted the elusive World Cup.", "TROPHY"),
                CareerMilestone("2023", "Historic 8th Ballon d'Or", "Extended his legendary record after Qatar 2022 masterclass.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.82f,
                yPct = 0.65f,
                primaryZoneName = "Right Half-Space & Attacking Midfield",
                secondaryZones = listOf("Right Wing", "Central Creator Zone", "Edge of Penalty Box")
            ),
            personalLife = PersonalLife(
                legalFullName = "Lionel Andrés Messi Cuccittini",
                dateOfBirth = "June 24, 1987",
                age = 37,
                birthplace = "Rosario, Santa Fe, Argentina",
                heightCm = 170,
                weightKg = 72,
                nicknames = listOf("La Pulga (The Flea)", "The GOAT", "El Diez", "Atomic Flea"),
                earlyLifeAndRoots = "Diagnosed with growth hormone deficiency as a boy in Rosario. When Newell's Old Boys could not afford the monthly injections, Barcelona scout Carles Rexach signed 13-year-old Messi on a famous paper napkin and paid for his medical treatment.",
                familyAndRelationships = "Married to his childhood sweetheart Antonela Roccuzzo since 2017. Together they have three sons: Thiago, Mateo, and Ciro. His father Jorge serves as his agent and his mother Celia inspired his signature skyward celebration.",
                philanthropyAndCauses = "Founded the Leo Messi Foundation in 2007, building pediatric oncology clinics, providing school supplies and healthcare in Argentina, Syria, and Kenya. Long-standing UNICEF Goodwill Ambassador.",
                businessAndInvestments = "Owns the MiM Hotels luxury chain across Spain and Andorra. Holds an ownership equity stake in Inter Miami CF, Mas+ hydration beverage, and digital media production company 525 Rosario.",
                endorsementsAndSponsors = listOf("Adidas (Lifetime Deal)", "Apple TV", "Hard Rock International", "Konami", "Budweiser", "LVMH Louis Vuitton"),
                hobbiesAndPassions = listOf("Sipping traditional Yerba Mate tea", "Playing EA Sports FC on PlayStation", "Acoustic Spanish guitar", "Playing with French mastiff dog Hulk"),
                funFactsAndTrivia = listOf(
                    "First Barcelona contract was signed on a cafeteria paper napkin in December 2000.",
                    "Points both index fingers to the heavens after every goal to honor his late grandmother Celia.",
                    "Always enters the pitch with his right foot first and touches the turf with his fingers.",
                    "Never speaks French publicly despite living in Paris for two full years."
                )
            )
        ),

        // 2. Cristiano Ronaldo
        Player(
            id = "ronaldo",
            name = "Cristiano Ronaldo",
            displayName = "C. Ronaldo",
            number = 7,
            position = PlayerPosition.ST,
            club = "Al-Nassr FC",
            clubBadgeColor = 0xFFFACC15,
            league = "Saudi Pro League",
            nationality = "Portugal",
            nationalityCode = "PT",
            flagEmoji = "🇵🇹",
            isProminent = true,
            prominentBadge = "All-Time Top Goalscorer (900+)",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=600&auto=format&fit=crop&q=80",
            overallRating = 91,
            formRating = 8.92f,
            marketValueEur = "€15M",
            contractUntil = "June 2025",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 81,
                shooting = 93,
                passing = 78,
                dribbling = 82,
                defending = 35,
                physical = 79,
                vision = 82,
                composure = 96
            ),
            careerTotals = CareerTotals(
                totalAppearances = 1250,
                totalGoals = 915,
                totalAssists = 255,
                totalMinutes = 104500,
                passAccuracyPct = 81.2f,
                shotConversionPct = 19.8f,
                duelsWonPct = 52.8f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Al-Nassr", "Saudi Pro League", 26, 25, 6, 8.45f, listOf("Arab Club Champions Cup")),
                SeasonRecord("2023/24", "Al-Nassr", "Saudi Pro League", 45, 44, 13, 8.65f, listOf("SPL Golden Boot (35 Goals record)")),
                SeasonRecord("2022/23", "Al-Nassr / Man Utd", "SPL / EPL", 35, 17, 4, 7.62f, listOf("Arab Club Championship")),
                SeasonRecord("2021/22", "Manchester United", "Premier League", 38, 24, 3, 7.92f, listOf("Sir Matt Busby POTY")),
                SeasonRecord("2020/21", "Juventus", "Serie A", 44, 36, 4, 8.12f, listOf("Capocannoniere (29 Goals)", "Coppa Italia")),
                SeasonRecord("2019/20", "Juventus", "Serie A", 46, 37, 7, 8.21f, listOf("Serie A Title"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 5,
                championsLeague = 5,
                worldCup = 0,
                continentalCup = 2, // UEFA Euro 2016, UEFA Nations League 2019
                domesticLeagueTitles = 7,
                domesticCups = 6,
                goldenBoots = 4,
                notableHonors = listOf(
                    "All-time top scorer in UEFA Champions League history (140 goals)",
                    "All-time top international goalscorer in men's football history (135+ goals)",
                    "First player ever to score in 5 consecutive World Cups",
                    "Real Madrid all-time top scorer (450 goals in 438 matches)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2003", "Manchester United Move", "Signed by Sir Alex Ferguson after dazzling in friendly for Sporting CP.", "TRANSFER"),
                CareerMilestone("2008", "First Champions League & Ballon d'Or", "Won double in Moscow with Manchester United.", "TROPHY"),
                CareerMilestone("2009", "World Record Real Madrid Transfer", "Joined Real Madrid for £80M in front of 80,000 at Bernabéu.", "TRANSFER"),
                CareerMilestone("2016", "UEFA Euro Championship with Portugal", "Coached from the touchline in Paris to bring Portugal their first major trophy.", "TROPHY"),
                CareerMilestone("2018", "Historic 3-Peat Champions League", "Lifted fifth UCL trophy with iconic bicycle kick vs Juventus.", "TROPHY"),
                CareerMilestone("2024", "900th Official Career Goal", "Became first man in recorded history to surpass 900 competitive career goals.", "RECORD")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.90f,
                yPct = 0.48f,
                primaryZoneName = "Penalty Box Center & Left Channel",
                secondaryZones = listOf("Far Post Heading Zone", "Left Inverted Cut-In", "Direct Free Kick Arc")
            ),
            personalLife = PersonalLife(
                legalFullName = "Cristiano Ronaldo dos Santos Aveiro",
                dateOfBirth = "February 5, 1985",
                age = 40,
                birthplace = "Funchal, Madeira, Portugal",
                heightCm = 187,
                weightKg = 85,
                nicknames = listOf("CR7", "El Bicho", "The Commander", "Mr. Champions League"),
                earlyLifeAndRoots = "Grew up in a working-class neighborhood of Santo António, Madeira. Named 'Ronaldo' after US President Ronald Reagan. Left home at age 12 to live alone at Sporting Lisbon's academy, where he was teased for his island accent.",
                familyAndRelationships = "In a relationship with model Georgina Rodríguez since 2016. Father to Cristiano Jr., twins Eva and Mateo, Alana Martina, and Bella Esmeralda. His mother Dolores Aveiro remains his biggest confidante.",
                philanthropyAndCauses = "Refuses to get tattoos so he can donate blood and bone marrow regularly. Paid for brain surgery for a 10-year-old fan, auctioned his 2013 Ballon d'Or for £600,000 for Make-A-Wish, and donated millions to Portuguese hospitals during COVID.",
                businessAndInvestments = "Global empire CR7 includes Pestana CR7 luxury hotels, CR7 underwear & eyewear lines, CR7 Crunch fitness gyms, hair transplant clinics, and his newly launched record-shattering YouTube channel UR Cristiano.",
                endorsementsAndSponsors = listOf("Nike (Lifetime $1B Deal)", "Armani", "Clear Shampoo", "Tag Heuer", "Herbalife", "Binance"),
                hobbiesAndPassions = listOf("World-class supercar collecting (Bugatti Centodieci, Ferrari Daytona)", "Cryotherapy and cold plunge routines", "High-stakes table tennis (notoriously unbeatable)", "Nutrition and sleep science"),
                funFactsAndTrivia = listOf(
                    "Has a standing vertical leap of 78 cm (higher than an average NBA player).",
                    "Launched his YouTube channel 'UR Cristiano' and gained 20 million subscribers in just 24 hours.",
                    "His famous 'SIUUU' celebration originated spontaneously during a pre-season friendly in Miami in 2013.",
                    "Eats 6 micro-meals a day and takes five 90-minute sleep cycles instead of an 8-hour stretch."
                )
            )
        ),

        // 3. Kylian Mbappé
        Player(
            id = "mbappe",
            name = "Kylian Mbappé",
            displayName = "K. Mbappé",
            number = 9,
            position = PlayerPosition.ST,
            club = "Real Madrid",
            clubBadgeColor = 0xFFFFFFFF,
            league = "La Liga",
            nationality = "France",
            nationalityCode = "FR",
            flagEmoji = "🇫🇷",
            isProminent = true,
            prominentBadge = "World Cup Final Hat-Trick Hero",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1579952363873-27f3bade9f55?w=600&auto=format&fit=crop&q=80",
            overallRating = 91,
            formRating = 8.80f,
            marketValueEur = "€180M",
            contractUntil = "June 2029",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 97,
                shooting = 90,
                passing = 80,
                dribbling = 92,
                defending = 36,
                physical = 78,
                vision = 84,
                composure = 91
            ),
            careerTotals = CareerTotals(
                totalAppearances = 465,
                totalGoals = 345,
                totalAssists = 160,
                totalMinutes = 37200,
                passAccuracyPct = 82.5f,
                shotConversionPct = 24.1f,
                duelsWonPct = 51.4f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Real Madrid", "La Liga", 28, 22, 6, 8.15f, listOf("UEFA Super Cup")),
                SeasonRecord("2023/24", "PSG", "Ligue 1", 48, 44, 10, 8.42f, listOf("Ligue 1", "Coupe de France", "Ligue 1 Top Scorer")),
                SeasonRecord("2022/23", "PSG", "Ligue 1", 43, 41, 10, 8.35f, listOf("Ligue 1", "World Cup Golden Boot (8 Goals)")),
                SeasonRecord("2021/22", "PSG", "Ligue 1", 46, 39, 26, 8.62f, listOf("Ligue 1 (Top Scorer & Top Assists)"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 0,
                worldCup = 1,
                continentalCup = 1, // UEFA Nations League 2021
                domesticLeagueTitles = 7,
                domesticCups = 4,
                goldenBoots = 6,
                notableHonors = listOf(
                    "FIFA World Cup Champion (2018 at age 19)",
                    "FIFA World Cup Golden Boot (2022, 8 goals including final hat-trick)",
                    "PSG All-Time Top Scorer (256 goals)",
                    "6x consecutive Ligue 1 Top Scorer"
                )
            ),
            milestones = listOf(
                CareerMilestone("2017", "Monaco Fairytale & Breakout", "Fired Monaco to Ligue 1 title and UCL semifinals at 18.", "DEBUT"),
                CareerMilestone("2018", "World Cup Winner in Moscow", "Scored in the World Cup Final vs Croatia, named Best Young Player.", "TROPHY"),
                CareerMilestone("2022", "World Cup Final Hat-Trick", "Became only the second man ever (after Geoff Hurst) to score a final hat-trick.", "RECORD"),
                CareerMilestone("2024", "Dream Real Madrid Unveiling", "Joined Real Madrid on a free transfer before 85,000 cheering Madridistas.", "TRANSFER")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.88f,
                yPct = 0.35f,
                primaryZoneName = "Left Half-Space & Burst Channel",
                secondaryZones = listOf("Central Penalty Box", "Counter-Attack Left Flank")
            ),
            personalLife = PersonalLife(
                legalFullName = "Kylian Mbappé Lottin",
                dateOfBirth = "December 20, 1998",
                age = 26,
                birthplace = "Bondy, Paris, France",
                heightCm = 178,
                weightKg = 75,
                nicknames = listOf("Donatello", "Kyky", "King Kylian"),
                earlyLifeAndRoots = "Raised in Bondy, an impoverished northeastern suburb of Paris. His father Wilfried is of Cameroonian descent and was his first football coach; his mother Fayza Lamari was a professional handball player of Algerian descent.",
                familyAndRelationships = "Very close to his younger adopted brother Jirès Kembo Ekoko and brother Ethan Mbappé (now playing in Ligue 1 for Lille). His mother Fayza manages his media and contract negotiations.",
                philanthropyAndCauses = "Founded 'Inspired by KM' foundation in 2020 to support 98 underprivileged Parisian children through education, cultural trips, and sports. Donated his entire 2018 World Cup bonus ($500,000) to children's charity Premiers de Cordée.",
                businessAndInvestments = "Purchased majority ownership stake in French Ligue 2 club SM Caen in 2024. Launched production studio Zebra Valley in partnership with WME Sports.",
                endorsementsAndSponsors = listOf("Nike", "Hublot Luxury Watches", "Oakley", "Dior (Global Fashion Ambassador)", "Sorare"),
                hobbiesAndPassions = listOf("Fluent in three languages (French, Spanish, English)", "NBA fanatic (close friend of LeBron James)", "Reading biographies of sporting legends", "Sneakerhead with 300+ pairs"),
                funFactsAndTrivia = listOf(
                    "Had posters of Cristiano Ronaldo covering his entire childhood bedroom wall in Bondy.",
                    "His arms-crossed chest celebration was invented by his younger brother Ethan after beating him in FIFA.",
                    "Refuses to promote alcohol, fast food, or betting companies in any endorsement contracts."
                )
            )
        ),

        // 4. Erling Haaland
        Player(
            id = "haaland",
            name = "Erling Haaland",
            displayName = "E. Haaland",
            number = 9,
            position = PlayerPosition.ST,
            club = "Manchester City",
            clubBadgeColor = 0xFF6EE7B7,
            league = "Premier League",
            nationality = "Norway",
            nationalityCode = "NO",
            flagEmoji = "🇳🇴",
            isProminent = true,
            prominentBadge = "Premier League Single-Season Record (36G)",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1543326727-cf6c39e8f84c?w=600&auto=format&fit=crop&q=80",
            overallRating = 91,
            formRating = 9.02f,
            marketValueEur = "€200M",
            contractUntil = "June 2027",
            preferredFoot = "Left",
            attributes = PlayerAttributes(
                pace = 89,
                shooting = 94,
                passing = 67,
                dribbling = 80,
                defending = 45,
                physical = 88,
                vision = 74,
                composure = 94
            ),
            careerTotals = CareerTotals(
                totalAppearances = 340,
                totalGoals = 280,
                totalAssists = 55,
                totalMinutes = 26500,
                passAccuracyPct = 74.0f,
                shotConversionPct = 28.6f,
                duelsWonPct = 56.4f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Man City", "Premier League", 24, 21, 3, 8.35f, listOf("FA Community Shield")),
                SeasonRecord("2023/24", "Man City", "Premier League", 45, 38, 6, 8.28f, listOf("Premier League", "PL Golden Boot (27 Goals)", "UEFA Super Cup")),
                SeasonRecord("2022/23", "Man City", "Premier League", 53, 52, 9, 8.65f, listOf("Historic Treble (UCL, EPL, FA Cup)", "European Golden Shoe", "PL Record 36 Goals")),
                SeasonRecord("2021/22", "Dortmund", "Bundesliga", 30, 29, 8, 8.18f, listOf("DFB-Pokal"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 1,
                worldCup = 0,
                continentalCup = 0,
                domesticLeagueTitles = 4,
                domesticCups = 2,
                goldenBoots = 3,
                notableHonors = listOf(
                    "European Golden Shoe (2022/23)",
                    "Fastest player to reach 50 Premier League goals (48 games)",
                    "UEFA Men's Player of the Year (2022/23)",
                    "Gerd Müller Trophy (2023)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2019", "Nine Goals in One Match", "Scored 9 goals for Norway U20 vs Honduras at the U20 World Cup.", "RECORD"),
                CareerMilestone("2020", "Champions League Sensation", "Scored a 20-minute hat-trick on his Dortmund debut.", "DEBUT"),
                CareerMilestone("2022", "Manchester City Transfer", "Joined Man City and scored 3 consecutive home hat-tricks.", "TRANSFER"),
                CareerMilestone("2023", "Continental Treble & 52 Goals", "Led City to UCL glory in Istanbul, shattering the Premier League scoring record.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.94f,
                yPct = 0.50f,
                primaryZoneName = "Penalty Box Danger Zone & 6-Yard Box",
                secondaryZones = listOf("Near Post Runs", "Aerial Cross Target Zone")
            ),
            personalLife = PersonalLife(
                legalFullName = "Erling Braut Haaland",
                dateOfBirth = "July 21, 2000",
                age = 24,
                birthplace = "Leeds, West Yorkshire, England",
                heightCm = 194,
                weightKg = 88,
                nicknames = listOf("The Terminator", "Daemon Targaryen", "The Viking", "Cyborg"),
                earlyLifeAndRoots = "Born in Leeds while his father Alf-Inge Haaland played for Leeds United. Moved back to Bryne, Norway at age three. Excelled in track and field and still holds the world record for the longest standing long jump by a five-year-old (1.63 meters).",
                familyAndRelationships = "In a relationship with Norwegian footballer Isabel Haugseng Johansen. Father Alf-Inge played in the Premier League for Nottingham Forest, Leeds, and Man City.",
                philanthropyAndCauses = "Purchased and distributed free match tickets and train travel for 200 Bryne FK supporters for their playoff match in Norway. Donates football gear to local youth clubs across rural Norway.",
                businessAndInvestments = "Invested in Prime Hydration, Norwegian health tech ventures, and luxury real estate in Marbella, Spain.",
                endorsementsAndSponsors = listOf("Nike ($25M/year boot deal)", "Breitling Luxury Watches", "Dolce & Gabbana", "Hyperice Recovery", "Viaplay"),
                hobbiesAndPassions = listOf("Meditation with Lotus celebration pose", "Blue-light blocking glasses before bed", "Eating heart and liver (ancestral carnivore diet)", "Farming on his tractor in Bryne"),
                funFactsAndTrivia = listOf(
                    "Sleeps with special mouth tape to encourage nasal breathing during the night.",
                    "Wakes up to the UEFA Champions League anthem as his morning phone alarm.",
                    "Drinks specially filtered water and drinks raw milk directly from local Norwegian farms."
                )
            )
        ),

        // 5. Jude Bellingham
        Player(
            id = "bellingham",
            name = "Jude Bellingham",
            displayName = "J. Bellingham",
            number = 5,
            position = PlayerPosition.CAM,
            club = "Real Madrid",
            clubBadgeColor = 0xFFFFFFFF,
            league = "La Liga",
            nationality = "England",
            nationalityCode = "GB",
            flagEmoji = "🏴󠁧󠁢󠁥󠁮󠁧󠁿",
            isProminent = true,
            prominentBadge = "Golden Boy & UCL Champion",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1517466787929-bc90951d0974?w=600&auto=format&fit=crop&q=80",
            overallRating = 90,
            formRating = 8.75f,
            marketValueEur = "€180M",
            contractUntil = "June 2029",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 82,
                shooting = 86,
                passing = 88,
                dribbling = 88,
                defending = 78,
                physical = 83,
                vision = 89,
                composure = 92
            ),
            careerTotals = CareerTotals(
                totalAppearances = 265,
                totalGoals = 62,
                totalAssists = 48,
                totalMinutes = 21400,
                passAccuracyPct = 86.8f,
                shotConversionPct = 21.0f,
                duelsWonPct = 61.5f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Real Madrid", "La Liga", 25, 11, 8, 8.20f, listOf("UEFA Super Cup")),
                SeasonRecord("2023/24", "Real Madrid", "La Liga", 42, 23, 13, 8.58f, listOf("La Liga", "UEFA Champions League", "La Liga POTY")),
                SeasonRecord("2022/23", "Dortmund", "Bundesliga", 42, 14, 7, 8.14f, listOf("Bundesliga Player of the Season")),
                SeasonRecord("2021/22", "Dortmund", "Bundesliga", 44, 6, 14, 7.82f, listOf("DFB-Pokal runner-up"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 1,
                worldCup = 0,
                continentalCup = 0,
                domesticLeagueTitles = 1,
                domesticCups = 1,
                goldenBoots = 0,
                notableHonors = listOf(
                    "Golden Boy Award Winner (2023)",
                    "Kopa Trophy Best U21 Player in World (2023)",
                    "La Liga Player of the Season (2023/24)",
                    "Birmingham City retired his #22 shirt at age 17"
                )
            ),
            milestones = listOf(
                CareerMilestone("2019", "Youngest Debutant in Birmingham History", "Made senior debut at 16 years and 38 days.", "DEBUT"),
                CareerMilestone("2020", "Dortmund Signing & Shirt Retired", "Moved to Bundesliga; boyhood club retired #22 in tribute.", "TRANSFER"),
                CareerMilestone("2023", "Inheriting Zidane's #5 at Real Madrid", "Completed €103M move to Real Madrid and scored stoppage time winners in both Clásicos.", "TRANSFER"),
                CareerMilestone("2024", "Wembley Champions League Glory", "Assisted Vinicius in the final against his former club Borussia Dortmund.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.72f,
                yPct = 0.52f,
                primaryZoneName = "Box-to-Box Engine & Central Attacking Third",
                secondaryZones = listOf("Late Penalty Box Surge", "Left Channel Pressing", "Edge of D Shooting")
            ),
            personalLife = PersonalLife(
                legalFullName = "Jude Victor William Bellingham",
                dateOfBirth = "June 29, 2003",
                age = 21,
                birthplace = "Stourbridge, West Midlands, England",
                heightCm = 186,
                weightKg = 77,
                nicknames = listOf("Belligol", "Jude", "The English Zidane"),
                earlyLifeAndRoots = "Grew up in Stourbridge. His father Mark Bellingham was a West Midlands police sergeant and a non-league legendary striker who scored over 700 amateur goals, inspiring Jude's goalscoring instincts.",
                familyAndRelationships = "His mother Denise lived with him in Dortmund and now in Madrid to support his lifestyle. Younger brother Jobe Bellingham currently stars for Sunderland AFC in the Championship.",
                philanthropyAndCauses = "Ambassador for charity Micheal Matthews Foundation, funding the construction of a school in Mombasa, Kenya for over 300 underprivileged children.",
                businessAndInvestments = "Signed major partnership with Louis Vuitton; starred in SKIMS men's launch campaign; investor in sports technology startups.",
                endorsementsAndSponsors = listOf("Adidas Predator (Headline Athlete)", "Louis Vuitton", "Lucozade Sport", "McDonald's Fun Football"),
                hobbiesAndPassions = listOf("Learning fluent Spanish with daily language tutors", "Watching younger brother Jobe's matches live", "Playing snooker and darts with teammates", "Listening to R&B and British hip-hop"),
                funFactsAndTrivia = listOf(
                    "Chose shirt #22 because a youth coach told him he could play as a #4 (DM), #8 (CM), and #10 (CAM) combined (4+8+10 = 22).",
                    "Famous arms-outstretched celebration with chest high was copied by thousands of athletes globally.",
                    "Learned Spanish so fast that he conducts post-match interviews in fluent Castilian."
                )
            )
        ),

        // 6. Vinicius Jr
        Player(
            id = "vinicius",
            name = "Vinícius Júnior",
            displayName = "Vinícius Jr.",
            number = 7,
            position = PlayerPosition.LW,
            club = "Real Madrid",
            clubBadgeColor = 0xFFFFFFFF,
            league = "La Liga",
            nationality = "Brazil",
            nationalityCode = "BR",
            flagEmoji = "🇧🇷",
            isProminent = true,
            prominentBadge = "2x Champions League Final Matchwinner",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=600&auto=format&fit=crop&q=80",
            overallRating = 90,
            formRating = 8.90f,
            marketValueEur = "€200M",
            contractUntil = "June 2027",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 96,
                shooting = 84,
                passing = 82,
                dribbling = 94,
                defending = 32,
                physical = 69,
                vision = 85,
                composure = 86
            ),
            careerTotals = CareerTotals(
                totalAppearances = 380,
                totalGoals = 115,
                totalAssists = 95,
                totalMinutes = 28900,
                passAccuracyPct = 80.5f,
                shotConversionPct = 17.5f,
                duelsWonPct = 52.0f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Real Madrid", "La Liga", 26, 17, 10, 8.40f, listOf("UEFA Super Cup")),
                SeasonRecord("2023/24", "Real Madrid", "La Liga", 39, 24, 11, 8.68f, listOf("UEFA Champions League", "La Liga", "Supercopa de España", "UCL Player of Season")),
                SeasonRecord("2022/23", "Real Madrid", "La Liga", 55, 23, 21, 8.35f, listOf("Copa del Rey", "FIFA Club World Cup")),
                SeasonRecord("2021/22", "Real Madrid", "La Liga", 52, 22, 20, 8.42f, listOf("UEFA Champions League (Scored Final Winner)", "La Liga"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 2,
                worldCup = 0,
                continentalCup = 0,
                domesticLeagueTitles = 3,
                domesticCups = 1,
                goldenBoots = 0,
                notableHonors = listOf(
                    "UEFA Champions League Player of the Season (2023/24)",
                    "Scored match-winning goal in two separate UCL Finals (2022 vs Liverpool, 2024 vs Dortmund)",
                    "Socrates Award for humanitarian and anti-racism leadership (2023)",
                    "Ballon d'Or Runner-up (2024)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2017", "Flamengo Sensation", "Sold to Real Madrid for €46M before playing a single senior match, at age 16.", "TRANSFER"),
                CareerMilestone("2022", "Paris UCL Final Winner", "Tapped in Valverde's cross to seal Real Madrid's 14th European Cup.", "TROPHY"),
                CareerMilestone("2023", "Inherited Iconic #7 Shirt", "Awarded Cristiano Ronaldo's historic number 7 jersey.", "RECORD"),
                CareerMilestone("2024", "Wembley UCL Final Goal", "Scored second UCL final goal and named best player in the competition.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.86f,
                yPct = 0.18f,
                primaryZoneName = "Left Touchline 1v1 Dribble Zone",
                secondaryZones = listOf("Inverted Cut-Back Box Penetration", "Left Channel Sprint Behind Line")
            ),
            personalLife = PersonalLife(
                legalFullName = "Vinícius José Paixão de Oliveira Júnior",
                dateOfBirth = "July 12, 2000",
                age = 24,
                birthplace = "São Gonçalo, Rio de Janeiro, Brazil",
                heightCm = 176,
                weightKg = 73,
                nicknames = listOf("Vini Jr", "Malvadeza", "Baila Vini"),
                earlyLifeAndRoots = "Raised in Porto Novo, a violent favela in São Gonçalo near Rio. His family lived in poverty in his uncle's house. Traveled 70 kilometers every day by bus to Flamengo's training academy.",
                familyAndRelationships = "Very close to his parents Vinícius Sr and Fernanda, and siblings. Kept all his childhood friends from São Gonçalo on his personal team.",
                philanthropyAndCauses = "Founded Instituto Vini Jr in 2021, deploying innovative educational software (Base app) in Brazilian public schools to teach underprivileged children through sports gamification.",
                businessAndInvestments = "Global anti-racism spokesperson for UNESCO; invested in Brazilian esports team LOUD and youth cultural hubs in Rio.",
                endorsementsAndSponsors = listOf("Nike", "Gatorade", "Pepsi", "PlayStation", "Golden Goose"),
                hobbiesAndPassions = listOf("Brazilian Samba and Pagode music", "Playing Call of Duty with Neymar", "Bailar (dancing salsa and funk celebrations)", "High-fashion sneakers"),
                funFactsAndTrivia = listOf(
                    "Won the prestigious Socrates Award at the Ballon d'Or ceremony for his anti-racism education work.",
                    "His motto 'Baila Vini Jr' became a worldwide viral hashtag supported by Pelé, Neymar, and Lewis Hamilton.",
                    "Still has his first bus pass laminated in his Madrid trophy room."
                )
            )
        ),

        // 7. Rodri
        Player(
            id = "rodri",
            name = "Rodri (Rodrigo Hernández)",
            displayName = "Rodri",
            number = 16,
            position = PlayerPosition.CDM,
            club = "Manchester City",
            clubBadgeColor = 0xFF6EE7B7,
            league = "Premier League",
            nationality = "Spain",
            nationalityCode = "ES",
            flagEmoji = "🇪🇸",
            isProminent = true,
            prominentBadge = "2024 Ballon d'Or Winner",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1560272564-c83b66b1ad12?w=600&auto=format&fit=crop&q=80",
            overallRating = 91,
            formRating = 9.20f,
            marketValueEur = "€130M",
            contractUntil = "June 2027",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 68,
                shooting = 82,
                passing = 91,
                dribbling = 84,
                defending = 87,
                physical = 85,
                vision = 92,
                composure = 97
            ),
            careerTotals = CareerTotals(
                totalAppearances = 490,
                totalGoals = 45,
                totalAssists = 40,
                totalMinutes = 39800,
                passAccuracyPct = 92.4f,
                shotConversionPct = 14.2f,
                duelsWonPct = 71.8f
            ),
            seasonHistory = listOf(
                SeasonRecord("2023/24", "Man City", "Premier League", 50, 9, 14, 8.68f, listOf("Ballon d'Or 2024 Winner", "Euro 2024 Champion", "Premier League")),
                SeasonRecord("2022/23", "Man City", "Premier League", 56, 4, 7, 8.42f, listOf("Historic Treble", "Scored UCL Final Winning Goal vs Inter", "UCL Player of Tournament")),
                SeasonRecord("2021/22", "Man City", "Premier League", 46, 7, 2, 7.95f, listOf("Premier League")),
                SeasonRecord("2020/21", "Man City", "Premier League", 53, 2, 5, 7.82f, listOf("Premier League", "Carabao Cup"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 1,
                championsLeague = 1,
                worldCup = 0,
                continentalCup = 2, // Euro 2024, Nations League 2023
                domesticLeagueTitles = 4,
                domesticCups = 3,
                goldenBoots = 0,
                notableHonors = listOf(
                    "2024 Ballon d'Or Winner (First defensive midfielder in decades)",
                    "UEFA Euro 2024 Player of the Tournament",
                    "74-match unbeaten streak in all club competitions (World Record)",
                    "Scored winner in 2023 UEFA Champions League Final in Istanbul"
                )
            ),
            milestones = listOf(
                CareerMilestone("2019", "City Record Signing", "Joined Pep Guardiola's Manchester City from Atlético Madrid for £62.8M.", "TRANSFER"),
                CareerMilestone("2023", "Istanbul Champions League Final Goal", "Curled home the 68th minute strike against Inter Milan to win City's first Treble.", "TROPHY"),
                CareerMilestone("2024", "Euro 2024 Triumph & Player of Tournament", "Anchored Spain's perfect 7-out-of-7 win campaign in Germany.", "TROPHY"),
                CareerMilestone("2024", "Crowned Ballon d'Or in Paris", "Arrived on crutches at Théâtre du Châtelet to lift the greatest individual prize in football.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.52f,
                yPct = 0.50f,
                primaryZoneName = "Center Circle & Deep Defensive Anchor",
                secondaryZones = listOf("Press Resistance Turn Zone", "Long Range Shooting Arc")
            ),
            personalLife = PersonalLife(
                legalFullName = "Rodrigo Hernández Cascante",
                dateOfBirth = "June 22, 1996",
                age = 28,
                birthplace = "Madrid, Spain",
                heightCm = 191,
                weightKg = 82,
                nicknames = listOf("Rodri", "The Computer", "The Anchor"),
                earlyLifeAndRoots = "Released by Atlético Madrid youth academy at age 17 for being 'too weak physically'. Joined Villarreal where he lived in student dorms at Universidad Jaume I while studying Business Administration.",
                familyAndRelationships = "In a long-term relationship with Laura, a doctor whom he met while they were both living in university halls. Remains grounded without personal social media.",
                philanthropyAndCauses = "Donates significant portions of his earnings to cancer research in Spain and supports youth education programs for underprivileged students in Valencia and Madrid.",
                businessAndInvestments = "Completed a full university degree in Business and Economics while playing professional football in the Premier League. Invests in sustainable green energy startups.",
                endorsementsAndSponsors = listOf("Puma Future Boot Deal"),
                hobbiesAndPassions = listOf("Zero social media accounts", "Playing acoustic classical guitar", "Reading economics journals and history books", "Spearfishing off the coast of Spain"),
                funFactsAndTrivia = listOf(
                    "Famously lived in student accommodation at university even after making his senior Villarreal La Liga debut.",
                    "Always tucks his shirt into his shorts on matchdays, a rare old-school tradition.",
                    "Refuses to have any tattoos or flashy jewelry, maintaining a completely low-profile life."
                )
            )
        ),

        // 8. Kevin De Bruyne
        Player(
            id = "debruyne",
            name = "Kevin De Bruyne",
            displayName = "K. De Bruyne",
            number = 17,
            position = PlayerPosition.CM,
            club = "Manchester City",
            clubBadgeColor = 0xFF6EE7B7,
            league = "Premier League",
            nationality = "Belgium",
            nationalityCode = "BE",
            flagEmoji = "🇧🇪",
            isProminent = true,
            prominentBadge = "Assist King & Playmaker Master",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=600&auto=format&fit=crop&q=80",
            overallRating = 91,
            formRating = 8.85f,
            marketValueEur = "€45M",
            contractUntil = "June 2025",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 72,
                shooting = 88,
                passing = 95,
                dribbling = 86,
                defending = 65,
                physical = 74,
                vision = 99,
                composure = 93
            ),
            careerTotals = CareerTotals(
                totalAppearances = 650,
                totalGoals = 155,
                totalAssists = 260,
                totalMinutes = 51200,
                passAccuracyPct = 83.5f,
                shotConversionPct = 14.8f,
                duelsWonPct = 54.0f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Man City", "Premier League", 22, 5, 12, 8.30f, listOf("FA Community Shield")),
                SeasonRecord("2023/24", "Man City", "Premier League", 26, 6, 18, 8.55f, listOf("Premier League", "Premier League record comeback")),
                SeasonRecord("2022/23", "Man City", "Premier League", 49, 10, 31, 8.60f, listOf("Historic Treble", "PL Playmaker of the Season")),
                SeasonRecord("2021/22", "Man City", "Premier League", 45, 19, 14, 8.42f, listOf("Premier League Player of the Season"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 1,
                worldCup = 0,
                continentalCup = 0,
                domesticLeagueTitles = 6,
                domesticCups = 5,
                goldenBoots = 0,
                notableHonors = listOf(
                    "2x PFA Players' Player of the Year (2020, 2021)",
                    "3x Premier League Playmaker of the Season",
                    "Fastest player in Premier League history to reach 100 assists",
                    "FIFA World Cup 3rd Place Bronze Medal (2018)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2012", "Chelsea Move & Rejection", "Sold by José Mourinho who doubted his tactical discipline.", "TRANSFER"),
                CareerMilestone("2015", "Wolfsburg Record Season", "Set Bundesliga record with 21 assists, named Footballer of the Year in Germany.", "RECORD"),
                CareerMilestone("2015", "Manchester City £55M Transfer", "Joined City where he became the tactical brain of Pep Guardiola's dynasty.", "TRANSFER"),
                CareerMilestone("2023", "Treble Masterclass", "Provided 31 assists across all competitions in City's historic Treble campaign.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.70f,
                yPct = 0.75f,
                primaryZoneName = "Right Half-Space Whipped Cross Zone",
                secondaryZones = listOf("Dead-Ball Delivery Corner", "Through-Ball Pocket", "Edge of Box Strikers")
            ),
            personalLife = PersonalLife(
                legalFullName = "Kevin De Bruyne",
                dateOfBirth = "June 28, 1991",
                age = 33,
                birthplace = "Drongen, Ghent, Belgium",
                heightCm = 181,
                weightKg = 70,
                nicknames = listOf("KDB", "The Ginger Prince", "The Maestro"),
                earlyLifeAndRoots = "Grew up in Drongen, East Flanders. Experienced a painful foster family rejection at age 14 while in Genk academy, which fueled his ferocious work ethic to prove doubters wrong.",
                familyAndRelationships = "Married to Michèle Lacroix since 2017. Proud father to three children: Mason Milian, Rome, and Suri.",
                philanthropyAndCauses = "Founded the Kevin De Bruyne Cup (KDB Cup), one of the world's most prestigious international U15 youth tournaments in Belgium.",
                businessAndInvestments = "Investor in sports health tech Therabody and creator of children's community sports facilities across Belgium.",
                endorsementsAndSponsors = listOf("Nike Phantom", "McDonald's Belgium", "Orange Telecom", "Secretlab"),
                hobbiesAndPassions = listOf("Baking cakes and gourmet pastries with his children", "Playing board games and Mario Kart", "Golfing with teammates", "Fluent in Dutch, French, English, and German"),
                funFactsAndTrivia = listOf(
                    "Can spot passing angles faster than anyone in world football, known as 'football radar vision'.",
                    "Famous for his heated on-pitch yell: 'LET ME TALK!' during a 2017 Champions League match with David Silva.",
                    "Has an entire international youth tournament named after him that features Barcelona, Chelsea, and PSG academies."
                )
            )
        ),

        // 9. Mohamed Salah
        Player(
            id = "salah",
            name = "Mohamed Salah",
            displayName = "M. Salah",
            number = 11,
            position = PlayerPosition.RW,
            club = "Liverpool FC",
            clubBadgeColor = 0xFFDC2626,
            league = "Premier League",
            nationality = "Egypt",
            nationalityCode = "EG",
            flagEmoji = "🇪🇬",
            isProminent = true,
            prominentBadge = "The Egyptian King (3x Golden Boot)",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1517466787929-bc90951d0974?w=600&auto=format&fit=crop&q=80",
            overallRating = 90,
            formRating = 9.05f,
            marketValueEur = "€55M",
            contractUntil = "June 2025",
            preferredFoot = "Left",
            attributes = PlayerAttributes(
                pace = 89,
                shooting = 89,
                passing = 84,
                dribbling = 88,
                defending = 44,
                physical = 76,
                vision = 86,
                composure = 90
            ),
            careerTotals = CareerTotals(
                totalAppearances = 620,
                totalGoals = 330,
                totalAssists = 160,
                totalMinutes = 49800,
                passAccuracyPct = 81.0f,
                shotConversionPct = 21.2f,
                duelsWonPct = 48.6f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Liverpool", "Premier League", 27, 21, 14, 8.65f, listOf("Carabao Cup")),
                SeasonRecord("2023/24", "Liverpool", "Premier League", 44, 25, 14, 8.25f, listOf("Carabao Cup")),
                SeasonRecord("2022/23", "Liverpool", "Premier League", 51, 30, 16, 8.10f, listOf("FA Community Shield")),
                SeasonRecord("2021/22", "Liverpool", "Premier League", 51, 31, 16, 8.52f, listOf("FA Cup", "Carabao Cup", "PL Golden Boot (23G)"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 1,
                worldCup = 0,
                continentalCup = 0, // 2x AFCON Runner-up
                domesticLeagueTitles = 1,
                domesticCups = 3,
                goldenBoots = 3,
                notableHonors = listOf(
                    "Premier League 38-game season scoring record (32 goals in 2017/18)",
                    "2x PFA Players' Player of the Year (2018, 2022)",
                    "Liverpool's all-time Premier League top goalscorer",
                    "FIFA Puskás Award winner (2018)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2017", "Anfield Arrival", "Signed for Liverpool from AS Roma and scored 44 goals in debut campaign.", "TRANSFER"),
                CareerMilestone("2019", "Champions League Glory in Madrid", "Scored early penalty vs Tottenham to lift Liverpool's 6th European Cup.", "TROPHY"),
                CareerMilestone("2020", "Historic 30-Year Premier League Title", "Spearheaded Liverpool's 99-point title-winning triumph.", "TROPHY"),
                CareerMilestone("2023", "All-Time Liverpool Premier League King", "Surpassed Robbie Fowler's 128-goal mark with a brace vs Man United.", "RECORD")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.84f,
                yPct = 0.80f,
                primaryZoneName = "Right Wing Cut-Inside & Far-Corner Curler",
                secondaryZones = listOf("Inverted Penalty Box Strike", "Right Channel Breakaway")
            ),
            personalLife = PersonalLife(
                legalFullName = "Mohamed Salah Hamed Mahrous Ghaly",
                dateOfBirth = "June 15, 1992",
                age = 32,
                birthplace = "Nagrig, Basyoun, Gharbia, Egypt",
                heightCm = 175,
                weightKg = 71,
                nicknames = listOf("The Egyptian King", "Mo", "Pharaoh"),
                earlyLifeAndRoots = "Grew up in the rural village of Nagrig. Had to change 5 buses each way, taking 4.5 hours every single day just to travel to Cairo to train with Arab Contractors FC.",
                familyAndRelationships = "Married Magi Sadeq in 2013 in Nagrig with thousands of villagers attending. Father to daughters Makka (named after Mecca) and Kayan.",
                philanthropyAndCauses = "Built an entire water treatment plant, girls' school, religious institute, and ambulance center in his home village of Nagrig. Gives thousands of dollars monthly to families in need across Egypt.",
                businessAndInvestments = "Real estate developments across Egypt and the UK; investor in fitness wellness and sports gear brands.",
                endorsementsAndSponsors = listOf("Adidas", "Pepsi", "Vodafone Egypt", "DHL Express", "Gucci"),
                hobbiesAndPassions = listOf("Daily gym and core workouts at 4 AM", "Reading literature and philosophy", "Yoga and mindfulness stretching", "Watching stand-up comedy"),
                funFactsAndTrivia = listOf(
                    "Stanford University study proved that Salah's arrival at Liverpool caused an 18.9% drop in hate crimes and Islamophobia across Merseyside.",
                    "Received more than 1 million write-in votes in the 2018 Egyptian presidential election despite not even running.",
                    "Performs the Islamic Sujud (prostration of gratitude) on the pitch after every single goal."
                )
            )
        ),

        // 10. Lamine Yamal
        Player(
            id = "yamal",
            name = "Lamine Yamal",
            displayName = "L. Yamal",
            number = 19,
            position = PlayerPosition.RW,
            club = "FC Barcelona",
            clubBadgeColor = 0xFF1D4ED8,
            league = "La Liga",
            nationality = "Spain",
            nationalityCode = "ES",
            flagEmoji = "🇪🇸",
            isProminent = true,
            prominentBadge = "Euro 2024 Champion at Age 17",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1522778119026-d647f0596c20?w=600&auto=format&fit=crop&q=80",
            overallRating = 87,
            formRating = 9.10f,
            marketValueEur = "€150M",
            contractUntil = "June 2030",
            preferredFoot = "Left",
            attributes = PlayerAttributes(
                pace = 90,
                shooting = 83,
                passing = 88,
                dribbling = 92,
                defending = 38,
                physical = 62,
                vision = 91,
                composure = 90
            ),
            careerTotals = CareerTotals(
                totalAppearances = 85,
                totalGoals = 18,
                totalAssists = 24,
                totalMinutes = 6200,
                passAccuracyPct = 83.2f,
                shotConversionPct = 16.4f,
                duelsWonPct = 54.8f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Barcelona", "La Liga", 25, 11, 14, 8.65f, listOf("La Liga Top Creator")),
                SeasonRecord("2023/24", "Barcelona", "La Liga", 50, 7, 10, 8.12f, listOf("Euro 2024 Champion", "Euro Young Player of Tournament", "Kopa Trophy Winner")),
                SeasonRecord("2022/23", "Barcelona", "La Liga", 1, 0, 0, 7.00f, listOf("La Liga Champion (Youngest in Barca history)"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 0,
                worldCup = 0,
                continentalCup = 1, // UEFA Euro 2024
                domesticLeagueTitles = 1,
                domesticCups = 0,
                goldenBoots = 0,
                notableHonors = listOf(
                    "Youngest player and goalscorer in European Championship history (16 years old)",
                    "UEFA Euro 2024 Young Player of the Tournament",
                    "Kopa Trophy 2024 Winner (Best U21 in world)",
                    "Golden Boy The Youngest Award (2023)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2023", "Barcelona First Team Debut at 15", "Debuted against Real Betis at 15 years, 9 months and 16 days.", "DEBUT"),
                CareerMilestone("2023", "Youngest La Liga Goalscorer", "Scored vs Granada at 16 years and 87 days.", "RECORD"),
                CareerMilestone("2024", "Euro 2024 Semi-Final Wondergoal", "Curled 25-meter screamer vs France in Munich, winning Goal of the Tournament.", "RECORD"),
                CareerMilestone("2024", "Crowned King of Europe", "Assisted Nico Williams in final vs England the day after his 17th birthday.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.85f,
                yPct = 0.78f,
                primaryZoneName = "Right Wing Inverted Playmaker",
                secondaryZones = listOf("Half-Space Trivela Cross", "Inside Box Dribble Pocket")
            ),
            personalLife = PersonalLife(
                legalFullName = "Lamine Yamal Nasraoui Ebana",
                dateOfBirth = "July 13, 2007",
                age = 17,
                birthplace = "Esplugues de Llobregat, Barcelona, Spain",
                heightCm = 180,
                weightKg = 68,
                nicknames = listOf("The Prodigy", "Golden Kid of Rocafonda", "Lamine"),
                earlyLifeAndRoots = "Grew up in Rocafonda, a humble working-class immigrant neighborhood in Mataró. His father Mounir is Moroccan and his mother Sheila is from Equatorial Guinea. Celebrates every goal with a '304' finger gesture representing the postal code of Rocafonda (08304).",
                familyAndRelationships = "Very close to his grandmother Fatima and baby brother Keyne. As a baby in 2007, Lionel Messi famously posed bathing him in a charity calendar photoshoot!",
                philanthropyAndCauses = "Supports community soccer clinics and youth recreation parks in Rocafonda to keep kids off the streets and in education.",
                businessAndInvestments = "Signed long-term global partnership with Adidas, receiving his own signature line at age 17.",
                endorsementsAndSponsors = listOf("Adidas F50 (Headline Young Star)", "Beats by Dre"),
                hobbiesAndPassions = listOf("Studied high school ESO homework during Euro 2024 camp", "Dancing TikToks with best friend Nico Williams", "Playing video games with academy teammates", "Listening to Afrobeat and Spanish urban rap"),
                funFactsAndTrivia = listOf(
                    "Passed his high school exams online while competing at Euro 2024 in Germany.",
                    "A 2007 photo of 20-year-old Lionel Messi holding 5-month-old baby Lamine in a plastic tub went viral 17 years later.",
                    "Wears the boots with the flags of both Morocco and Equatorial Guinea stitched into the heels alongside Spain."
                )
            )
        ),

        // 11. Harry Kane
        Player(
            id = "kane",
            name = "Harry Kane",
            displayName = "H. Kane",
            number = 9,
            position = PlayerPosition.ST,
            club = "FC Bayern Munich",
            clubBadgeColor = 0xFFDC2626,
            league = "Bundesliga",
            nationality = "England",
            nationalityCode = "GB",
            flagEmoji = "🏴󠁧󠁢󠁥󠁮󠁧󠁿",
            isProminent = true,
            prominentBadge = "European Golden Shoe Winner (36G)",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=600&auto=format&fit=crop&q=80",
            overallRating = 90,
            formRating = 8.85f,
            marketValueEur = "€100M",
            contractUntil = "June 2027",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 69,
                shooting = 93,
                passing = 86,
                dribbling = 82,
                defending = 49,
                physical = 83,
                vision = 90,
                composure = 95
            ),
            careerTotals = CareerTotals(
                totalAppearances = 590,
                totalGoals = 370,
                totalAssists = 110,
                totalMinutes = 48500,
                passAccuracyPct = 80.2f,
                shotConversionPct = 23.4f,
                duelsWonPct = 53.5f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Bayern Munich", "Bundesliga", 26, 24, 9, 8.50f, emptyList()),
                SeasonRecord("2023/24", "Bayern Munich", "Bundesliga", 45, 44, 12, 8.62f, listOf("European Golden Shoe (36 Goals)", "Bundesliga Torjägerkanone", "UCL Top Scorer")),
                SeasonRecord("2022/23", "Tottenham", "Premier League", 49, 32, 5, 8.08f, emptyList()),
                SeasonRecord("2021/22", "Tottenham", "Premier League", 50, 27, 10, 7.84f, emptyList())
            ),
            trophies = TrophyCabinet(
                ballonDor = 0,
                championsLeague = 0,
                worldCup = 0,
                continentalCup = 0, // 2x Euro Runner-up (2020, 2024)
                domesticLeagueTitles = 0,
                domesticCups = 0,
                goldenBoots = 5,
                notableHonors = listOf(
                    "European Golden Shoe (2023/24 with 36 Bundesliga goals)",
                    "3x Premier League Golden Boot (2016, 2017, 2021)",
                    "England Men's All-Time Top Goalscorer (69+ goals)",
                    "Tottenham Hotspur All-Time Top Goalscorer (280 goals)"
                )
            ),
            milestones = listOf(
                CareerMilestone("2015", "Breakthrough 31-Goal Season", "PFA Young Player of the Year at Tottenham.", "DEBUT"),
                CareerMilestone("2018", "World Cup Golden Boot", "Captained England to World Cup semi-final, scoring 6 goals in Russia.", "RECORD"),
                CareerMilestone("2023", "Surpassing Wayne Rooney", "Became England's all-time record goalscorer in Naples against Italy.", "RECORD"),
                CareerMilestone("2023", "Bayern Munich €100M Move", "Scored 4 hat-tricks in debut Bundesliga campaign.", "TRANSFER")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.88f,
                yPct = 0.50f,
                primaryZoneName = "Penalty Box Center & False-Nine Playmaker Drop",
                secondaryZones = listOf("Quarterback Deep Drop Passing", "Far-Post Header Runs")
            ),
            personalLife = PersonalLife(
                legalFullName = "Harry Edward Kane MBE",
                dateOfBirth = "July 28, 1993",
                age = 31,
                birthplace = "Walthamstow, London, England",
                heightCm = 188,
                weightKg = 86,
                nicknames = listOf("The Hurricane", "King Kane", "Captain Harry"),
                earlyLifeAndRoots = "Grew up in Chingford, London. Attended the same school as David Beckham (Chingford Foundation School). Was famously released by Arsenal's youth academy at age eight for being 'chubby'.",
                familyAndRelationships = "Married childhood sweetheart Katie Goodland in 2019, whom he knew since primary school. Parents to four children: Ivy, Vivienne, Louis, and Henry.",
                philanthropyAndCauses = "Founded the Harry Kane Foundation to change perceptions of mental health in young people and partnered with the Premier League on youth wellness campaigns.",
                businessAndInvestments = "Invested in sustainable tech startups, padel tennis clubs across the UK, and owns significant commercial property in London.",
                endorsementsAndSponsors = listOf("Skechers Football (Headline Global Athlete)", "Fortnite", "Amazon Prime", "Insidetracker"),
                hobbiesAndPassions = listOf("Scratch golfer with a near-scratch handicap (plays in celebrity pro-ams)", "NFL fanatic and passionate New England Patriots fan", "Walking his two Labrador dogs Brady and Wilson"),
                funFactsAndTrivia = listOf(
                    "Named his two Labrador dogs Brady and Wilson after NFL quarterbacks Tom Brady and Russell Wilson.",
                    "Was awarded an MBE (Member of the Order of the British Empire) for services to football by Queen Elizabeth II.",
                    "Aspires to become an NFL kicker after retiring from professional football."
                )
            )
        ),

        // 12. Luka Modrić
        Player(
            id = "modric",
            name = "Luka Modrić",
            displayName = "L. Modrić",
            number = 10,
            position = PlayerPosition.CM,
            club = "Real Madrid",
            clubBadgeColor = 0xFFFFFFFF,
            league = "La Liga",
            nationality = "Croatia",
            nationalityCode = "HR",
            flagEmoji = "🇭🇷",
            isProminent = true,
            prominentBadge = "Ballon d'Or & 6x Champions League Winner",
            localDrawableRes = null,
            photoUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?w=600&auto=format&fit=crop&q=80",
            overallRating = 87,
            formRating = 8.60f,
            marketValueEur = "€6M",
            contractUntil = "June 2025",
            preferredFoot = "Right",
            attributes = PlayerAttributes(
                pace = 68,
                shooting = 76,
                passing = 89,
                dribbling = 88,
                defending = 72,
                physical = 65,
                vision = 94,
                composure = 98
            ),
            careerTotals = CareerTotals(
                totalAppearances = 860,
                totalGoals = 88,
                totalAssists = 145,
                totalMinutes = 68200,
                passAccuracyPct = 89.5f,
                shotConversionPct = 12.0f,
                duelsWonPct = 58.2f
            ),
            seasonHistory = listOf(
                SeasonRecord("2024/25", "Real Madrid", "La Liga", 28, 3, 6, 8.10f, listOf("Most Decorated Player in Real Madrid History (27 Trophies)")),
                SeasonRecord("2023/24", "Real Madrid", "La Liga", 46, 2, 8, 8.25f, listOf("UEFA Champions League", "La Liga")),
                SeasonRecord("2022/23", "Real Madrid", "La Liga", 52, 6, 6, 8.18f, listOf("Copa del Rey", "FIFA World Cup Bronze Medal"))
            ),
            trophies = TrophyCabinet(
                ballonDor = 1,
                championsLeague = 6, // Joint record with Dani Carvajal & Paco Gento
                worldCup = 0,
                continentalCup = 0,
                domesticLeagueTitles = 4,
                domesticCups = 2,
                goldenBoots = 0,
                notableHonors = listOf(
                    "2018 Ballon d'Or Winner (Broke Messi-Ronaldo 10-year duopoly)",
                    "FIFA World Cup Golden Ball (2018)",
                    "Most decorated player in Real Madrid's 122-year history (27 titles)",
                    "6x UEFA Champions League Winner"
                )
            ),
            milestones = listOf(
                CareerMilestone("2012", "Real Madrid Signing", "Joined Madrid from Tottenham; voted 'worst signing in La Liga' before proving all critics wrong.", "TRANSFER"),
                CareerMilestone("2014", "La Décima Assist in Lisbon", "Delivered the 93rd-minute corner for Sergio Ramos's immortal header.", "TROPHY"),
                CareerMilestone("2018", "World Cup Final & Ballon d'Or", "Captained Croatia to their first World Cup Final in Moscow and won Ballon d'Or.", "TROPHY"),
                CareerMilestone("2024", "Record 6th Champions League Trophy", "Lifted sixth European Cup at Wembley Stadium.", "TROPHY")
            ),
            pitchPosition = PitchCoordinates(
                xPct = 0.60f,
                yPct = 0.40f,
                primaryZoneName = "Central Engine & Trivela Maestro Channel",
                secondaryZones = listOf("Right Half-Space Escaper", "Defensive Transition Interceptor")
            ),
            personalLife = PersonalLife(
                legalFullName = "Luka Modrić",
                dateOfBirth = "September 9, 1985",
                age = 39,
                birthplace = "Zadar, SR Croatia, Yugoslavia",
                heightCm = 172,
                weightKg = 66,
                nicknames = listOf("Lukita", "The Maestro", "The Trivela Magician"),
                earlyLifeAndRoots = "Grew up during the Croatian War of Independence. His beloved grandfather Luka was killed by Serbian paramilitaries near their home in Modrići. Lived in refugee hotels in Zadar for seven years, practicing football in grenade-damaged parking lots.",
                familyAndRelationships = "Married Vanja Bosnić in 2010. Father to three children: Ivano, Ema, and Sofia. His family resides in Madrid and Zagreb.",
                philanthropyAndCauses = "Silently finances hospital equipment and pediatric wings in Zadar and Zagreb. Rebuilt destroyed community infrastructure in war-torn Croatian villages.",
                businessAndInvestments = "Co-owns several commercial hospitality ventures in Croatia and invests in Croatian youth sports development.",
                endorsementsAndSponsors = listOf("Nike Mercurial (Signature Player)"),
                hobbiesAndPassions = listOf("Tennis and padel", "Spending summer sailing the Dalmatian coast", "Reading Croatian historical fiction", "Studying coaching tactics"),
                funFactsAndTrivia = listOf(
                    "Master of the 'Trivela' (outside of the right foot pass) considered the best in football history.",
                    "At 38 years old, ran over 10 km per 90 minutes in Champions League knockout games.",
                    "Published his best-selling autobiography 'My Game' with a foreword by Sir Alex Ferguson."
                )
            )
        )
    )

    fun getPlayerById(id: String): Player? {
        return players.find { it.id.equals(id, ignoreCase = true) }
    }

    fun getProminentPlayers(): List<Player> {
        return players.filter { it.isProminent }
    }

    fun filterPlayers(
        query: String = "",
        category: PositionCategory = PositionCategory.ALL,
        prominentOnly: Boolean = false
    ): List<Player> {
        return players.filter { player ->
            val matchesQuery = query.isBlank() ||
                    player.name.contains(query, ignoreCase = true) ||
                    player.club.contains(query, ignoreCase = true) ||
                    player.nationality.contains(query, ignoreCase = true)

            val matchesCategory = category == PositionCategory.ALL ||
                    player.position.category == category

            val matchesProminent = !prominentOnly || player.isProminent

            matchesQuery && matchesCategory && matchesProminent
        }
    }
}
