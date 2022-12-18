-- 1
SELECT p.producte_titol, e.estil_nom, p.producte_actiu, p.producte_tipus
FROM PRODUCTE p JOIN ESTIL e ON e.estil_id = p.producte_estil

WHERE ($P{ionActiu} IS NULL OR $P{ionActiu} = '' OR $P{ionActiu} = p.producte_actiu)
AND
($P{ionTipus} IS NULL OR $P{ionTipus} = '' OR
$P{ionTipus} = p.producte_tipus)
AND
($P{ionTitol} IS NULL OR $P{ionTipus} = '' OR
$P{ionTipus} = p.producte_titol)
ORDER BY p.producte_id ASC

-- 2
-- CANCO
SELECT p.producte_titol, e.estil_nom, p.producte_actiu, p.producte_tipus, c.canco_any_creacio, c.canco_durada, a.artista_nom
FROM PRODUCTE p JOIN ESTIL e ON e.estil_id = p.producte_estil
JOIN CANCO c ON c.canco_id = p.producte_id
JOIN ARTISTA a ON a.artista_id = c.canco_interpet;

--ALBUM
SELECT p.producte_titol, e.estil_nom, p.producte_actiu, p.producte_tipus, a.album_any_creacio, a.album_durada
FROM PRODUCTE p JOIN ESTIL e ON e.estil_id = p.producte_estil
JOIN ALBUM a ON a.album_id = p.producte_id;

--LLISTA
SELECT p.producte_titol, e.estil_nom, p.producte_actiu, p.producte_tipus, l.llista_durada
FROM PRODUCTE p JOIN ESTIL e ON e.estil_id = p.producte_estil
JOIN LLISTA l ON l.llista_id = p.producte_id;

