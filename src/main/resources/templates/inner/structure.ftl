<#-- 递归  宏定义 -->
<#macro dirTreeBuild curDir>
<#list curDir.nodes as node>
 <#if (node.simpleClassName=="LinkNode") >
	<link href="${node.href}" name="${node.name}"/>
 </#if>
 <#if (node.simpleClassName=="DirNode") >
	<dir name="${node.name}" >
		<@dirTreeBuild curDir = node />
	</dir>
 </#if>
</#list>
</#macro>
<site logo="${logo}">
	<topnav>
<@dirTreeBuild curDir = topnavDir />
	</topnav>
	<sidebar>
<@dirTreeBuild curDir = sidebarDir />
	</sidebar>
</site>