(ns elevator)

(def up \()
(def down \))

(defn floor-counts [directions]
	(into {up 0, down 0} (frequencies directions)))

(defn arrival-floor [directions]
	(let [counts (floor-counts directions)
			  ups    (counts up)
				downs  (counts down)]
		(- ups downs)))