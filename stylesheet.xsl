<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns="http://www.w3.org/1999/xhtml">
    <xsl:output method="xml" doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" doctype-public="-//W3C//DTD XHTML 1.1//EN"/>

    <xsl:template match="/">
        <html>
            <head>
                <style>
                    th,td,table {border:1px solid black;border-collapse:collapse;padding: 2px 3px;}
                </style>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="solution">
        <table style="margin-bottom:5px">
            <xsl:call-template name="header"/>
            <xsl:call-template name="rows"/>
        </table>
    </xsl:template>

    <xsl:template name="header">
        <tr>
            <th>House</th>
            <xsl:for-each select="house">
                <th>
                    <xsl:value-of select="@position"/>
                </th>
            </xsl:for-each>
        </tr>
    </xsl:template>

    <xsl:template name="rows">
        <xsl:param name="pos" select="2"/>
        <xsl:if test="$pos &lt;= count(house[position() = 1]/@*)">
            <tr>
                <xsl:variable name="name" select="name(house[position() = 1]/@*[position() = $pos])"/>
                <th>
                    <xsl:value-of select="$name"/>
                </th>
                <xsl:for-each select="house">
                    <td>
                        <xsl:value-of select="@*[name() = $name]"/>
                    </td>
                </xsl:for-each>
            </tr>
            <xsl:call-template name="rows">
                <xsl:with-param name="pos" select="$pos+1"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>